package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 2:15 AM)
 */
@Quality(Stage.TESTED)
public abstract class InsertionSorter<E extends Comparable<E>> implements Sorter<E> {

    protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
        int placement = length;
        for (int j = 0; j < length; j ++) {
            if (comparator.compare(items[j], item) > 0) {
                placement = j;
                break;
            }
        }
        return placement;
    }

}
