package com.mmnaseri.cs.clrs.ch09.sp;

import com.mmnaseri.cs.clrs.ch09.s3.LinearSelector;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

import static com.mmnaseri.cs.clrs.ch09.SelectionUtils.selectPartition;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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
        return selectPartition(comparator, items, from, to, pivot);
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
