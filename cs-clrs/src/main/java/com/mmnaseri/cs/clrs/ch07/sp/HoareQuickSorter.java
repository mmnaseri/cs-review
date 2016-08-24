package com.mmnaseri.cs.clrs.ch07.sp;

import com.mmnaseri.cs.clrs.ch07.s1.QuickSorter;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (6/6/15, 4:45 PM)
 */
@Quality(Stage.TESTED)
public class HoareQuickSorter<E extends Comparable<E>> extends QuickSorter<E> {

    private final Comparator<E> comparator;

    public HoareQuickSorter(Comparator<E> comparator) {
        super(comparator);
        this.comparator = comparator;
    }

    @Override
    protected int partition(E[] items, int from, int to) {
        final E pivot = items[from];
        int left = from + 1;
        int right = to - 1;
        while (left < right) {
            while (right > left && comparator.compare(items[right], pivot) >= 0) {
                right --;
            }
            while (left < right && comparator.compare(items[left], pivot) < 0) {
                left ++;
            }
            if (left < right) {
                ArrayUtils.swap(items, left, right);
            }
        }
        if (comparator.compare(pivot, items[left]) > 0) {
            ArrayUtils.swap(items, left, from);
            return left;
        } else {
            return from;
        }
    }

}
