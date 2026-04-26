package cache;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev, next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getters for Spring to convert to JSON
    public K getKey() { return key; }
    public V getValue() { return value; }
}