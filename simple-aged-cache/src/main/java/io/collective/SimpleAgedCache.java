package io.collective;

import java.time.Clock;
import java.util.HashMap;

public class SimpleAgedCache {
    private Clock clock; //similar to a self.__var uder def__init__(self) in py
    private MAP<Object, Object> cache; //dict in py
    private MAP<Object, Short> expirations; //retension in ms goes up to 4000 only


    public SimpleAgedCache(Clock clock) {
        this.clock = clock; //init the clock with clock param, this = similar to self in py
        this.cache = new HashMap<>();
        this.expirations = new HashMap<>();
    }

    public SimpleAgedCache() {
    }

    public void put(Object key, Object value, int retentionInMillis) {
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public Object get(Object key) {
        return null;
    }
}

