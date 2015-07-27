package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class MapDataStore<I extends Indexed<K>, K extends Comparable<K>> implements DataStore<I, K> {
    
    private final Map<K, I> map = new HashMap<>();
    
    @Override
    public I read(K key) {
        return map.get(key);
    }

    @Override
    public void write(K key, I data) {
        map.put(key, data);
    }

}
