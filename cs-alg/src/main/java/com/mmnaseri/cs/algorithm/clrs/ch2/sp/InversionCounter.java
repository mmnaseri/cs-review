package com.mmnaseri.cs.algorithm.clrs.ch2.sp;

import java.lang.reflect.Array;

/**
 * todo check again when you are awake
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:39 AM)
 */
public class InversionCounter<E extends Comparable<E>> {

    @SuppressWarnings("unchecked")
    private int merge(E[] array, int from, int mid, int to) {
        final E[] left = (E[]) Array.newInstance(array.getClass().getComponentType(), mid - from);
        final E[] right = (E[]) Array.newInstance(array.getClass().getComponentType(), to - mid);
        System.arraycopy(array, from, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);
        int leftCursor = 0;
        int rightCursor = 0;
        int cursor = from;
        int count = 0;
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
            } else if (rightItem.compareTo(leftItem) < 0) {
                array[cursor] = rightItem;
                rightCursor ++;
                count ++;
            } else {
                array[cursor] = leftItem;
                leftCursor ++;
            }
            cursor ++;
        }
        return count;
    }

    protected int count(E[] items, int from, int to) {
        if (to - from < 2) {
            return 0;
        }
        int count = 0;
        int mid = from + (to - from) / 2;
        count += count(items, from, mid);
        count += count(items, mid, to);
        count += merge(items, from, mid, to);
        return count;
    }

    public int count(E[] items) {
        return count(items, 0, items.length);
    }

}
