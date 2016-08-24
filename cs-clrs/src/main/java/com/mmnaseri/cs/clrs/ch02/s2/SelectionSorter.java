package com.mmnaseri.cs.clrs.ch02.s2;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 2:49 AM)
 */
@Quality(Stage.DOCUMENTED)
public class SelectionSorter<E extends Comparable<E>> implements Sorter<E> {

    private final Comparator<E> comparator;

    public SelectionSorter(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * This method implements the "selection" sort algorithm by considering the shrinking tail of the array (which contains
     * the full array at the start) and then finding the local minimum and placing it at the beginning of the array.
     * @param items    the items to be sorted
     */
    @Override
    @Complexity("O(n^2)")
    public void sort(E[] items) {
        for (int i = 0; i < items.length; i ++) {
            int localMinimum = -1;
            for (int j = i; j < items.length; j ++) {
                if (localMinimum < 0 || comparator.compare(items[j], items[localMinimum]) < 0) {
                    localMinimum = j;
                }
            }
            ArrayUtils.swap(items, i, localMinimum);
        }
    }

}
