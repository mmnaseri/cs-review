package com.mmnaseri.cs.clrs.ch21;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/2/15, 2:50 AM)
 */
public interface DisjointSet<E extends Element<I>, I> {

    Element<I> create(I representative);

    Element<I> find(E item);

    Element<I> union(E first, E second);

    Set<I> elements(E item);

}
