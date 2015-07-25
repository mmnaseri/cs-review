package com.mmnaseri.cs.clrs.ch16.custom;

import java.util.Map;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/25/15, 1:54 PM)
 */
public abstract class AbstractMappingBoundedCache<K, V> extends AbstractBoundedCache<K, V> {

    public AbstractMappingBoundedCache() {
    }

    public AbstractMappingBoundedCache(EvictionPolicy evictionPolicy) {
        super(evictionPolicy);
    }

    public AbstractMappingBoundedCache(int capacity) {
        super(capacity);
    }

    public AbstractMappingBoundedCache(int capacity, EvictionPolicy evictionPolicy) {
        super(capacity, evictionPolicy);
    }

    @Override
    protected Set<K> keys() {
        return getStorage().keySet();
    }

    protected abstract Map<K, CacheValue<V>> getStorage();

    @Override
    protected boolean has(K key) {
        return getStorage().containsKey(key);
    }

    @Override
    protected CacheValue<V> read(K key) {
        return getStorage().get(key);
    }

    @Override
    protected void write(K key, CacheValue<V> cacheValue) {
        getStorage().put(key, cacheValue);
    }

    @Override
    protected void delete(K key) {
        getStorage().remove(key);
    }

    @Override
    public int size() {
        return getStorage().size();
    }

}
