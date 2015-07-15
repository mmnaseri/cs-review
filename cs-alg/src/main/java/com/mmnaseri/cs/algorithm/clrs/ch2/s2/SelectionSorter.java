package com.mmnaseri.cs.algorithm.clrs.ch2.s2;

import com.mmnaseri.cs.algorithm.common.ArrayUtils;
import com.mmnaseri.cs.algorithm.common.Sorter;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:49 AM)
 */
public class SelectionSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public SelectionSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(E[] items) {
        for (int i = 0; i < items.length; i ++) {
            int localMinimum = -1;
            for (int j = i; j < items.length; j ++) {
                if (localMinimum < 0 || comparator.compare(items[j], items[localMinimum]) < 0) {
                    localMinimum = j;
                }
            }
            ArrayUtils.swap(items, i, localMinimum);
        }
    }

}
