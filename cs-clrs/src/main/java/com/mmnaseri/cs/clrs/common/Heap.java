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

    E get(int index);

    int change(int index, E newValue);

    void add(E item);

    boolean isEmpty();

}
