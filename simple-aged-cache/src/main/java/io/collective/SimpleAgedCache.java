package io.collective;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class SimpleAgedCache {
    private Clock clock;
    private Map<Object, Object> cache;
    private Map<Object, Long> expirations;

    public SimpleAgedCache(Clock clock) {
        this.clock = clock;
        this.cache = new HashMap<>();
        this.expirations = new HashMap<>();
    }

    public SimpleAgedCache() {
        this.clock = Clock.systemDefaultZone();
        this.cache = new HashMap<>();
        this.expirations = new HashMap<>();
    }

    public void put(Object key, Object value, int retentionInMillis) {
        cache.put(key, value);
        expirations.put(key, clock.millis() + retentionInMillis);
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }

    public int size() {
        long currentTime = clock.millis();
        Iterator<Map.Entry<Object, Long>> it = expirations.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Long> entry = it.next();
            if (entry.getValue() < currentTime) {
                cache.remove(entry.getKey());
                it.remove();
            }
        }
        return cache.size();
    }

    public Object get(Object key) {
        Object value = cache.get(key);
        Long expirationTime = expirations.get(key);

        if (value != null && expirationTime != null && expirationTime < clock.millis()) {
            cache.remove(key);
            expirations.remove(key);
            return null;
            } else {
                return value;
            }
        }
    }
