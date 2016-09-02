package com.mmnaseri.cs.clrs.ch05.custom;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;

import java.util.Comparator;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (9/2/16, 2:36 PM)
 */
public class StoogeSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public StoogeSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(E[] items) {
        sort(items, 0, items.length - 1);
    }

    private void sort(E[] items, int from, int to) {
        if (to < from) {
            return;
        }
        if (comparator.compare(items[to], items[from]) < 0) {
            ArrayUtils.swap(items, from, to);
        }
        if (to - from > 1) {
            final int pivot = (to - from + 1) / 3;
            sort(items, from, to - pivot);
            sort(items, from + pivot, to);
            sort(items, from, to - pivot);
        }
    }

}
