package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Finder;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Arrays;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:16 AM)
 */
@Quality(Stage.TESTED)
public class BinarySearchFinder<E extends Comparable<E>> implements Finder<E> {

    @SafeVarargs
    @Override
    public final int find(E needle, E... items) {
        Arrays.sort(items);
        return find(items, needle, 0, items.length);
    }

    private int find(E[] items, E needle, int from, int to) {
        if (to < from || from < 0 || to > items.length || items.length == 0) {
            return -1;
        }
        int mid = from + (to - from) / 2;
        final int comparison = needle.compareTo(items[mid]);
        if (comparison == 0) {
            return mid;
        } else if (to - from > 1) {
            if (comparison < 0) {
                return find(items, needle, 0, mid);
            } else {
                return find(items, needle, mid, to);
            }
        } else {
            return -1;
        }
    }

}
