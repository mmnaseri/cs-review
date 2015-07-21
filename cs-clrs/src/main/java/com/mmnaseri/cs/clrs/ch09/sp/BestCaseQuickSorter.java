package com.mmnaseri.cs.clrs.ch09.sp;

import com.mmnaseri.cs.clrs.ch09.s3.LinearSelector;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 9:05 PM)
 */
@Quality(Stage.TESTED)
public class BestCaseQuickSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;
    private final LinearSelector<E> selector;

    public BestCaseQuickSorter(Comparator<E> comparator) {
        this.comparator = comparator;
        selector = new LinearSelector<>(comparator);
    }

    private int partition(E[] items, int from, int to, int pivot) {
        ArrayUtils.swap(items, pivot, to - 1);
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

    private void sort(E[] items, int from, int to) {
        if (from < to - 1) {
            if (to - from == 2) {
                if (comparator.compare(items[from], items[to - 1]) > 0) {
                    ArrayUtils.swap(items, from, to - 1);
                }
                return;
            }
            int median = (int) Math.ceil((from + to) / 2.0) - 1;
            final int pivot = selector.select(items, null, 0, items.length, median);
            int middle = partition(items, from, to, pivot);
            sort(items, from, middle);
            sort(items, middle + 1, to);
        }
    }

    @Override
    public void sort(E[] items) {
        sort(items, 0, items.length);
    }

}
