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
