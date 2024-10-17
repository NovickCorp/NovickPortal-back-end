package com.novick.customers.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    private final CacheManager cacheManager;

    public CacheController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @DeleteMapping("/cache/{key}")
    public String evictFromCache(@PathVariable String key) {
        var cache = cacheManager.getCache(key);
        if (cache != null) {
            cache.invalidate();
            return String.format("Cache %s Evicted successfully.", key);
        }

        return String.format("%s not found in %s", key, cacheManager.getCacheNames());
    }

    @GetMapping("/cache")
    public String cache() {
        return cacheManager.getCacheNames().toString();
    }
}
