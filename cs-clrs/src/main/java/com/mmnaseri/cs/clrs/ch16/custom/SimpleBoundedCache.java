package com.mmnaseri.cs.clrs.ch16.custom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/25/15, 1:55 PM)
 */
public class SimpleBoundedCache<K, V> extends AbstractMappingBoundedCache<K, V> {

    private final Map<K, CacheValue<V>> storage = new HashMap<>();

    public SimpleBoundedCache() {
    }

    public SimpleBoundedCache(EvictionPolicy evictionPolicy) {
        super(evictionPolicy);
    }

    public SimpleBoundedCache(int capacity) {
        super(capacity);
    }

    public SimpleBoundedCache(int capacity, EvictionPolicy evictionPolicy) {
        super(capacity, evictionPolicy);
    }

    @Override
    protected Map<K, CacheValue<V>> getStorage() {
        return storage;
    }

}
