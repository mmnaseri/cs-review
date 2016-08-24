package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/31/15, 10:02 PM)
 */
public interface MergeableHeap<E extends Comparable<E>> extends Heap<E> {

    void merge(Heap<E> heap);

}
