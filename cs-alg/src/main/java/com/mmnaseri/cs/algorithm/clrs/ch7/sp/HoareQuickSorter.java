package com.mmnaseri.cs.algorithm.clrs.ch7.sp;

import com.mmnaseri.cs.algorithm.clrs.ch7.s1.QuickSorter;
import com.mmnaseri.cs.algorithm.common.ArrayUtils;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 4:45 PM)
 */
public class HoareQuickSorter<E extends Comparable<E>> extends QuickSorter<E> {

    public HoareQuickSorter(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected int partition(E[] items, int from, int to) {
        final E pivot = items[from];
        int left = from;
        int right = to - 1;
        while (true) {
            while (right < left && pivot.compareTo(items[right]) < 0) {
                right --;
            }
            while (right < left && pivot.compareTo(items[left]) > 0) {
                left ++;
            }
            if (left < right) {
                ArrayUtils.swap(items, left, right);
            } else {
                return left;
            }
        }
    }

}
