package com.mmnaseri.cs.clrs.ch18.s1;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/27/15)
 */
public interface NodeStore<K extends Comparable<K>> {

    List<K> read(K node);

    void write(K node, List<K> children);

}
