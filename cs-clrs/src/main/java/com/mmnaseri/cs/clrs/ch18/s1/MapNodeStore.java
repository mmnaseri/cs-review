package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class MapNodeStore<K extends Comparable<K>> implements NodeStore<K> {

    private final Map<K, List<K>> map = new HashMap<>();

    @Override
    public List<K> read(K node) {
        return map.get(node);
    }

    @Override
    public void write(K node, List<K> children) {
        map.put(node, children);
    }

}
