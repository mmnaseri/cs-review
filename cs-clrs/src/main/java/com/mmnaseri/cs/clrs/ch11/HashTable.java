package com.mmnaseri.cs.clrs.ch11;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
public interface HashTable<E> {

    E get(int index);

    void put(int index, E item);

    void delete(int index);

    int getSize();

    boolean isEmpty();

    int getCapacity();

}
