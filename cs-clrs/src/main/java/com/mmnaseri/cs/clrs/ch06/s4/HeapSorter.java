package com.mmnaseri.cs.clrs.ch06.s4;

import com.mmnaseri.cs.clrs.common.Heap;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (6/6/15, 3:58 PM)
 */
@Quality(Stage.TESTED)
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
