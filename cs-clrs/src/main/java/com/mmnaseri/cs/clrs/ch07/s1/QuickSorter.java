package com.mmnaseri.cs.clrs.ch07.s1;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (6/6/15, 4:22 PM)
 */
@Quality(Stage.TESTED)
public class QuickSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public QuickSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private void sort(E[] items, int from, int to) {
        if (from < to - 1) {
            if (to - from == 2) {
                if (comparator.compare(items[from], items[to - 1]) > 0) {
                    ArrayUtils.swap(items, from, to - 1);
                }
                return;
            }
            int middle = partition(items, from, to);
            sort(items, from, middle);
            sort(items, middle + 1, to);
        }
    }

    protected int partition(E[] items, int from, int to) {
        final E partition = items[to - 1];
        int smaller = from - 1;
        int seen = from;
        while (seen < to - 1) {
            if (comparator.compare(partition, items[seen]) >= 0) {
                smaller ++;
                ArrayUtils.swap(items, smaller, seen);
            }
            seen ++;
        }
        ArrayUtils.swap(items, smaller + 1, to - 1);
        return smaller + 1;
    }

    @Override
    public void sort(E[] items) {
        sort(items, 0, items.length);
    }

}
