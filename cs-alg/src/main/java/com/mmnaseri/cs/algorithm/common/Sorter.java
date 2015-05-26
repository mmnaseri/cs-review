package com.mmnaseri.cs.algorithm.common;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:19 AM)
 */
public interface Sorter<E extends Comparable<E>> {

    void sort(E[] items);

}
