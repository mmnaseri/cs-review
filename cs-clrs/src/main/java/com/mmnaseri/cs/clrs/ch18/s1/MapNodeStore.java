package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public class MapNodeStore<K extends Comparable<K>> implements NodeStore<K> {

    private final Map<K, Map<Integer, NodeDefinition<K>>> definitions = new HashMap<>();

    @Override
    public NodeDefinition<K> read(K parent, int index) {
        return definitions.containsKey(parent) ? definitions.get(parent).get(index) : null;
    }

    @Override
    public void write(K parent, int index, NodeDefinition<K> definition) {
        if (!definitions.containsKey(parent)) {
            definitions.put(parent, new HashMap<Integer, NodeDefinition<K>>());
        }
        definitions.get(parent).put(index, definition);
    }

}
