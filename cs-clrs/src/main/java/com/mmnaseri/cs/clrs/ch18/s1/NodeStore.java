package com.mmnaseri.cs.clrs.ch18.s1;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public interface NodeStore<K extends Comparable<K>> {

    NodeDefinition<K> read(K parent, int index);

    void write(K parent, int index, NodeDefinition<K> definition);

}
