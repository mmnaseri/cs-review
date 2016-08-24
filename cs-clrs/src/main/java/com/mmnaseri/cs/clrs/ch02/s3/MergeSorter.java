package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 2:58 AM)
 */
@Quality(Stage.DOCUMENTED)
public class MergeSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public MergeSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method merges the two halves of an array, given that each half is sorted individually
     * @param array    the array
     * @param from     the starting point of the array
     * @param mid      the middle of the array
     * @param to       the end of the array
     */
    @Complexity("O(n)")
    @SuppressWarnings("unchecked")
    protected void merge(E[] array, int from, int mid, int to) {
        //let's create two arrays for the left and right portions of the original array
        final E[] left = (E[]) Array.newInstance(array.getClass().getComponentType(), mid - from);
        final E[] right = (E[]) Array.newInstance(array.getClass().getComponentType(), to - mid);
        System.arraycopy(array, from, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);
        //we need two cursors, each of which are pointing at the current point of interest in either array
        int leftCursor = 0;
        int rightCursor = 0;
        //we need a cursor which points to the point at which the winning item should be written
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

    /**
     * This method sorts the indicated portion of the array by first sorting its two halves, and then merging the sorted havles
     * using {@link #merge(Comparable[], int, int, int)}
     * @param items    the array
     * @param from     the beginning of the target portion
     * @param to       the end of the target portion
     */
    @Complexity("O(n.lg(n))")
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
    public final void sort(E[] items) {
        sort(items, 0, items.length);
    }

}
