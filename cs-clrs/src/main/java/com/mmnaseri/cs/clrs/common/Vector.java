package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
public interface Vector<E> extends Iterable<E> {

    E get(int index);

    void set(int index, E value);

    int size();

}
