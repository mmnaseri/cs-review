package com.mmnaseri.cs.clrs.ch2.sp;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:39 AM)
 */
public class MergeInversionCounter<E extends Comparable<E>> implements InversionCounter<E> {

    private final Comparator<E> comparator;

    public MergeInversionCounter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private int merge(E[] array, int from, int mid, int to) {
        final E[] left = (E[]) Array.newInstance(array.getClass().getComponentType(), mid - from);
        final E[] right = (E[]) Array.newInstance(array.getClass().getComponentType(), to - mid);
        System.arraycopy(array, from, left, 0, left.length);
        System.arraycopy(array, mid, right, 0, right.length);
        int leftIndex = 0;
        int rightIndex = 0;
        int inversions = 0;
        boolean counted = false;
        for (int cursor = from; cursor < to; cursor ++) {
            if (rightIndex >= right.length) {
                System.arraycopy(left, leftIndex, array, cursor, left.length - leftIndex);
                break;
            } else if (leftIndex >= left.length) {
                System.arraycopy(right, rightIndex, array, cursor, right.length - rightIndex);
                break;
            }
            if (!counted && comparator.compare(left[leftIndex], right[rightIndex]) > 0) {
                inversions += left.length - leftIndex;
                counted = true;
            }
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                array[cursor] = left[leftIndex];
                leftIndex++;
            } else {
                counted = false;
                array[cursor] = right[rightIndex];
                rightIndex++;
            }
        }
        return inversions;
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

    @Override
    @SafeVarargs
    public final int count(E... items) {
        return count(items, 0, items.length);
    }

}
