package com.mmnaseri.cs.clrs.ch6.s4;

import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 3:58 PM)
 */
public class HeapSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Heap<E> heap;

    public HeapSorter(Heap<E> heap) {
        this.heap = heap;
    }

    @Override
    public void sort(E[] items) {
        heap.clear();
        for (E item : items) {
            heap.add(item);
        }
        for (int i = 0; i < items.length; i ++) {
            items[i] = heap.pop();
        }
    }

}
