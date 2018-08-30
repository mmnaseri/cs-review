package com.mmnaseri.cs.clrs.ch02.sp;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * Bubble sort iterates on the elements of the array from the beginning and compares each element with the
 * next element. If the element is larger, it swaps the elements. After one round of iteration, the largest
 * element is the last element of the array.
 *
 * Now that the last element is sorted, we need to iterate again till the next largest element is positioned correctly.
 * We continue this operation till all elements are sorted. If all elements are reversely sorted, number of iterations
 * times the number of swaps will become N square which is the worst case scenario.
 *
 * The following code implements the algorithm from the end to the beginning with two for loops.
 *
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
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
