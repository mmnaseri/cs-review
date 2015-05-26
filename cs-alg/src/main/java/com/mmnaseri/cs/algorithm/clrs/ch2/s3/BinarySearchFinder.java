package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import com.mmnaseri.cs.algorithm.common.Finder;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:16 AM)
 */
public class BinarySearchFinder<E extends Comparable<E>> implements Finder<E> {

    @Override
    public int find(E[] items, E needle) {
        Arrays.sort(items);
        return find(items, needle, 0, items.length);
    }

    private int find(E[] items, E needle, int from, int to) {
        if (to < from) {
            return -1;
        }
        int mid = from + (to - from) / 2;
        final int comparison = needle.compareTo(items[mid]);
        if (comparison == 0) {
            return mid;
        } else if (comparison < 0) {
            return find(items, needle, 0, mid);
        } else {
            return find(items, needle, mid, to);
        }
    }

}
