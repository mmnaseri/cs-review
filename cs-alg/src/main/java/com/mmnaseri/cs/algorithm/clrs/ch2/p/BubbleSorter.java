package com.mmnaseri.cs.algorithm.clrs.ch2.p;

import com.mmnaseri.cs.algorithm.common.Sorter;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:30 AM)
 */
public class BubbleSorter<E extends Comparable<E>> implements Sorter<E> {

    private void swap(E[] array, int first, int second) {
        final E temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    @Override
    public void sort(E[] items) {
        for (int i = 0; i < items.length - 1; i ++) {
            for (int j = items.length - 1; j > i; j --) {
                if (items[j].compareTo(items[j - 1]) < 0) {
                    swap(items, j, j - 1);
                }
            }
        }
    }

}
