package com.mmnaseri.cs.clrs.ch2.s3;

import com.mmnaseri.cs.clrs.common.Sorter;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:58 AM)
 */
public class MergeSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public MergeSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private void merge(E[] array, int from, int mid, int to) {
        final E[] left = (E[]) Array.newInstance(array.getClass().getComponentType(), mid - from);
        final E[] right = (E[]) Array.newInstance(array.getClass().getComponentType(), to - mid);
        System.arraycopy(array, from, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);
        int leftCursor = 0;
        int rightCursor = 0;
        int cursor = from;
        while (cursor < to) {
            final E leftItem;
            final E rightItem;
            if (leftCursor < left.length) {
                leftItem = left[leftCursor];
            } else {
                leftItem = null;
            }
            if (rightCursor < right.length) {
                rightItem = right[rightCursor];
            } else {
                rightItem = null;
            }
            if (leftItem == null) {
                array[cursor] = rightItem;
                rightCursor ++;
            } else if (rightItem == null) {
                array[cursor] = leftItem;
                leftCursor ++;
            } else if (comparator.compare(rightItem, leftItem) < 0) {
                array[cursor] = rightItem;
                rightCursor ++;
            } else {
                array[cursor] = leftItem;
                leftCursor ++;
            }
            cursor ++;
        }
    }

    protected void sort(E[] items, int from, int to) {
        if (to - from < 2) {
            return;
        }
        int mid = from + (to - from) / 2;
        sort(items, from, mid);
        sort(items, mid, to);
        merge(items, from, mid, to);
    }

    @Override
    public void sort(E[] items) {
        sort(items, 0, items.length);
    }

}
