package com.mmnaseri.cs.algorithm.clrs.ch2.p;

import com.mmnaseri.cs.algorithm.clrs.ch2.s1.InsertionSorter;
import com.mmnaseri.cs.algorithm.clrs.ch2.s3.MergeSorter;
import com.mmnaseri.cs.algorithm.common.Sorter;

import java.lang.reflect.Array;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:26 AM)
 */
public class InsertionMergeSorter<E extends Comparable<E>> extends MergeSorter<E> {

    private final Sorter<E> insertionSorter = new InsertionSorter<>();
    private final int cross;

    public InsertionMergeSorter() {
        this(4);
    }

    public InsertionMergeSorter(int cross) {
        this.cross = cross;
    }

    @Override
    protected void sort(E[] items, int from, int to) {
        if (to - from <= cross) {
            //noinspection unchecked
            final E[] array = (E[]) Array.newInstance(items.getClass().getComponentType(), to - from);
            System.arraycopy(items, from, array, 0, to - from);
            insertionSorter.sort(array);
            System.arraycopy(array, 0, items, from, to - from);
            return;
        }
        super.sort(items, from, to);
    }

}
