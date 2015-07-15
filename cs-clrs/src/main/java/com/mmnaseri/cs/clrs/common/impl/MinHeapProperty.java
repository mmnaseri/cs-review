package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.HeapProperty;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 3:51 PM)
 */
public class MinHeapProperty<E extends Comparable<E>> implements HeapProperty<E> {

    @Override
    public int compare(E first, E second) {
        return first.compareTo(second);
    }

}
