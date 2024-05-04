package io.collective;

import java.time.Clock;
import java.util.HashMap;

public class SimpleAgedCache {
    private Clock clock; //similar to a self.__var uder def__init__(self) in py
    private MAP<Object, Object> cache; //dict in py
    private MAP<Object, Long> expirations; //retension in ms goes up to 4000 only but then clock.millis is a long type


    public SimpleAgedCache(Clock clock) {
        this.clock = clock; //init the clock with clock param, this = similar to self in py
    }

    public SimpleAgedCache() {
        this.cache = new HashMap<>();
        this.expirations = new HashMap<>();
    }

    public void put(Object key, Object value, int retentionInMillis) {
        cache.put(key, value);
        expirations.put(key, clock.millis() + retentionInMillis);
    }

    public boolean isEmpty() {
        return cache.isEmpty(); //map has an inbuilt isEmpty() method
    }

    public int size() {
        return cache.size() //map has an inbuilt size() method 
    }

    public Object get(Object key) {
        Object val = cache.get(key)
        return val;
    }
}

