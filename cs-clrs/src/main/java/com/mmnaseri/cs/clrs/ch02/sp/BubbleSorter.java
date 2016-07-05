package com.mmnaseri.cs.clrs.ch02.sp;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 3:30 AM)
 */
@Quality(Stage.DOCUMENTED)
public class BubbleSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public BubbleSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * This algorithm sorts an input array by smaller items sink down the array
     * @param items    the items to be sorted
     */
    @Override
    @Complexity("O(n^2)")
    public void sort(E[] items) {
        for (int i = 0; i < items.length - 1; i ++) {
            for (int j = items.length - 1; j > i; j --) {
                if (comparator.compare(items[j], items[j - 1]) < 0) {
                    ArrayUtils.swap(items, j, j - 1);
                }
            }
        }
    }

}
