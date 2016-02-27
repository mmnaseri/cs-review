package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public interface Vector<E> extends Iterable<E> {

    E get(int index);

    void set(int index, E value);

    int size();

}
