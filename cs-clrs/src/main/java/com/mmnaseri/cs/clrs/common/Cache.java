package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/25/15, 1:42 PM)
 */
public interface Cache<K, V> {

    boolean contains(K key);

    V get(K key);

    void put(K key, V value);

    boolean evict(K key);

    int size();

}
