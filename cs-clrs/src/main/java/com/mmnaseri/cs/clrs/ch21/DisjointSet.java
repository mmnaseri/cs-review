package com.mmnaseri.cs.clrs.ch21;

import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 2:50 AM)
 */
@SuppressWarnings("UnusedReturnValue")
public interface DisjointSet<E extends Element<I>, I> {

  E create(I representative);

  E find(E item);

  E union(E first, E second);

  Set<I> elements(E item);

  Set<E> sets();
}
