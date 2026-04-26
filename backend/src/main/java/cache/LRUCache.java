package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized V get(K key) {
        if (!map.containsKey(key)) return null;
        Node<K, V> node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public synchronized void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (map.size() >= capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            Node<K, V> newNode = new Node<>(key, value);
            addNode(newNode);
            map.put(key, newNode);
        }
    }

    private void moveToHead(Node<K, V> node) {
        removeNode(node);
        addNode(node);
    }

    private void addNode(Node<K, V> node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public synchronized List<Node<K, V>> getAllNodes() {
        List<Node<K, V>> nodes = new ArrayList<>();
        Node<K, V> curr = head.next;
        while (curr != tail) {
            nodes.add(curr);
            curr = curr.next;
        }
        return nodes;
    }
}