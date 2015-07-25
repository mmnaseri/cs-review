package com.mmnaseri.cs.clrs.ch16.custom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/25/15, 1:56 PM)
 */
public class ConcurrentBoundedCache<K, V> extends AbstractMappingBoundedCache<K, V> {

    private final Map<K, CacheValue<V>> storage = new ConcurrentHashMap<>();

    public ConcurrentBoundedCache() {
    }

    public ConcurrentBoundedCache(EvictionPolicy evictionPolicy) {
        super(evictionPolicy);
    }

    public ConcurrentBoundedCache(int capacity) {
        super(capacity);
    }

    public ConcurrentBoundedCache(int capacity, EvictionPolicy evictionPolicy) {
        super(capacity, evictionPolicy);
    }

    @Override
    protected Map<K, CacheValue<V>> getStorage() {
        return storage;
    }

}
