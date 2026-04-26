package cache;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cache")
@CrossOrigin(origins = "*") // Allows React to talk to Java
public class CacheController {
    private final LRUCache<String, String> cache = new LRUCache<>(5);

    @GetMapping("/data")
    public List<Node<String, String>> getCache() {
        return cache.getAllNodes();
    }

    @PostMapping("/put")
    public void put(@RequestParam String key, @RequestParam String value) {
        cache.put(key, value);
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        return cache.get(key);
    }
}