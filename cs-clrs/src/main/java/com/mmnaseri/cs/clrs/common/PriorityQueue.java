package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/2/15, 11:43 AM)
 */
public interface PriorityQueue<E extends Comparable<E>> extends Heap<E> {

    E get(int index);

    int change(int index, E newValue);

}
