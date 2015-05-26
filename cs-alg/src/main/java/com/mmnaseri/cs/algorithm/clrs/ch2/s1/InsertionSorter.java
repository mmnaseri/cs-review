package com.mmnaseri.cs.algorithm.clrs.ch2.s1;

import com.mmnaseri.cs.algorithm.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:15 AM)
 */
public class InsertionSorter<E extends Comparable<E>> implements Sorter<E> {

    @Override
    public void sort(E[] items) {
        for (int i = 0; i < items.length; i++) {
            E item = items[i];
            int placement = i;
            for (int j = 0; j < i; j ++) {
                if (items[j].compareTo(item) > 0) {
                    placement = j;
                    break;
                }
            }
            /**
             * This call to System.arraycopy is equivalent to:
             * for (int j = i; j > placement; j --) {
             *     items[j] = items[j - 1];
             * }
             */
            System.arraycopy(items, placement, items, placement + 1, i - placement);
            items[placement] = item;
        }
    }

}
