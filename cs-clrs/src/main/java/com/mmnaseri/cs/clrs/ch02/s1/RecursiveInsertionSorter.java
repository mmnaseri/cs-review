package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (8/27/15)
 */
@Quality(Stage.TESTED)
public class RecursiveInsertionSorter<E extends Comparable<E>> extends InsertionSorter<E> {

    private final Comparator<E> comparator;

    public RecursiveInsertionSorter(Comparator<E> comparator) {
        super(comparator);
        this.comparator = comparator;
    }

    @Override
    public void sort(E[] items) {
        sort(items, 1);
    }

    private void sort(E[] items, int sorted) {
        if (sorted >= items.length) {
            return;
        }
        final E item = items[sorted];
        int placement = findPlacement(items, item, sorted, comparator);
        System.arraycopy(items, placement, items, placement + 1, sorted - placement);
        items[placement] = item;
        sort(items, sorted + 1);
    }

}
