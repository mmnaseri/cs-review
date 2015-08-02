package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 3:17 PM)
 */
public interface Heap<E extends Comparable<E>> {

    void clear();

    E peek();

    E pop();

    int size();

    void add(E item);

    boolean isEmpty();

}
