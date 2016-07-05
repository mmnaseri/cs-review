package com.mmnaseri.cs.clrs.common;

/**
 * This interface represents the common problem of sorting a number of items with the only assumption being that
 * some sort of comparison can be made between individual items of the array
 *
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:19 AM)
 */
public interface Sorter<E extends Comparable<E>> {

    /**
     * @param items    the items to be sorted
     */
    void sort(E[] items);

}
