package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/27/15)
 */
@Quality(Stage.TESTED)
public class FasterBinaryRecursiveInsertionSorter<E extends Comparable<E>> extends BinaryRecursiveInsertionSorter<E> {

    public FasterBinaryRecursiveInsertionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * This method tries to cut the running time by a constant factor or offset by treating the edge cases of when the
     * item is smaller than all items and when the item is larger than all items as special cases.
     * @param items         all the items
     * @param item          the item which we want to place
     * @param length        the length of the array which should be visited
     * @param comparator    the comparator
     * @return the expected position of the item in the given array
     */
    @Override
    protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
        if (comparator.compare(items[0], item) > 0) {
            return 0;
        } else if (comparator.compare(items[length - 1], item) < 0) {
            return length;
        }
        return super.findPlacement(items, item, length, comparator);
    }

}
