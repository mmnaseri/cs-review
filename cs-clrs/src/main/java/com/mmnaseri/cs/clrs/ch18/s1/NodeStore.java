package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public interface NodeStore<K extends Comparable<K>> {

    NodeDefinition<K> read(UUID id, int child);

    void write(UUID id, NodeDefinition<K> definition);

    void delete(UUID id);

}
