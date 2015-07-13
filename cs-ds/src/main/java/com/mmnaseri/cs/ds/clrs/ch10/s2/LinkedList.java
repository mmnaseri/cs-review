package com.mmnaseri.cs.ds.clrs.ch10.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 10:19 PM)
 */
public interface LinkedList<E> {

    int getSize();

    boolean isEmpty();

    E get(int index);

    int find(E needle);

    void insert(int index, E item);

    void add(E item);

    void delete(E item);

    void delete(int index);

}
