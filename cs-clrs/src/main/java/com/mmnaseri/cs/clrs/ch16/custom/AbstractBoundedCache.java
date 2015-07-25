package com.mmnaseri.cs.clrs.ch16.custom;

import com.mmnaseri.cs.clrs.common.Cache;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/25/15, 1:46 PM)
 */
public abstract class AbstractBoundedCache<K, V> implements Cache<K, V> {

    public static final int DEFAULT_CAPACITY = 256;
    public static final EvictionPolicy DEFAULT_EVICTION_POLICY = EvictionPolicy.LEAST_FREQUENTLY_USED;
    private static final CacheValue<?> NULL = new CacheValue<>(null);
    public static final int DEFAULT_THRESHOLD = 0;
    private final int capacity;
    private final EvictionPolicy evictionPolicy;

    public AbstractBoundedCache() {
        this(DEFAULT_EVICTION_POLICY);
    }

    public AbstractBoundedCache(EvictionPolicy evictionPolicy) {
        this(DEFAULT_CAPACITY, evictionPolicy);
    }

    public AbstractBoundedCache(int capacity) {
        this(capacity, DEFAULT_EVICTION_POLICY);
    }

    public AbstractBoundedCache(int capacity, EvictionPolicy evictionPolicy) {
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public boolean contains(K key) {
        return has(key);
    }

    @Override
    public V get(K key) {
        final CacheValue<V> value = read(key);
        return value.get();
    }

    @Override
    public void put(K key, V value) {
        ensureCapacity();
        //noinspection unchecked
        write(key, value == null ? (CacheValue<V>) NULL : new CacheValue<>(value));
    }

    @Override
    public boolean evict(K key) {
        if (!contains(key)) {
            return false;
        }
        delete(key);
        return true;
    }

    protected synchronized void ensureCapacity() {
        if (size() >= capacity + DEFAULT_THRESHOLD) {
            final List<K> keys = new LinkedList<>(keys());
            Collections.sort(keys, new CacheKeyComparator<>(this));
            final List<K> removed = new LinkedList<>();
            while (keys.size() > capacity) {
                removed.add(keys.remove(0));
            }
            for (K removedItem : removed) {
                evict(removedItem);
            }
        }
    }

    protected abstract Set<K> keys();

    protected abstract boolean has(K key);

    protected abstract CacheValue<V> read(K key);

    protected abstract void write(K key, CacheValue<V> cacheValue);

    protected abstract void delete(K key);

    protected static class CacheValue<V> {

        private final V value;
        private final Date creationTime;
        private Date accessTime;
        private int accessCount;

        public CacheValue(V value) {
            this.value = value;
            this.creationTime = new Date();
            this.accessTime = creationTime;
            this.accessCount = 1;
        }

        public V get() {
            return value;
        }

        public Date getAccessTime() {
            return accessTime;
        }

        public int getAccessCount() {
            return accessCount;
        }

        public Date getCreationTime() {
            return creationTime;
        }

        public void access() {
            accessCount ++;
            accessTime = new Date();
        }

    }

    private static class CacheKeyComparator<K, V> implements Comparator<K> {

        private final AbstractBoundedCache<K, V> cache;

        private CacheKeyComparator(AbstractBoundedCache<K, V> cache) {
            this.cache = cache;
        }

        @Override
        public int compare(K first, K second) {
            final CacheValue<V> firstValue = cache.read(first);
            final CacheValue<V> secondValue = cache.read(second);
            final EvictionPolicy evictionPolicy = cache.evictionPolicy;
            if (evictionPolicy.equals(EvictionPolicy.LEAST_FREQUENTLY_USED)) {
                return Integer.compare(firstValue.getAccessCount(), secondValue.getAccessCount());
            } else if (evictionPolicy.equals(EvictionPolicy.LEAST_RECENTLY_USED)) {
                return firstValue.getAccessTime().compareTo(secondValue.getAccessTime());
            } else if (evictionPolicy.equals(EvictionPolicy.OLDEST_FIRST)) {
                return secondValue.getCreationTime().compareTo(firstValue.getCreationTime());
            }
            return 0;
        }

    }

}
