package com.mmnaseri.cs.clrs.ch09.s2;

import com.mmnaseri.cs.clrs.ch09.Selector;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 5:47 PM)
 */
@Quality(Stage.TESTED)
public class RandomizedSelector<E extends Comparable<E>> implements Selector<E> {

    private final Comparator<E> comparator;

    public RandomizedSelector(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected int partition(E[] items, int from, int to) {
        final int index = Math.max(from, Math.min(new Random().nextInt(to - from) + from, to - 1));
        ArrayUtils.swap(items, index, to - 1);
        final E partition = items[to - 1];
        int smaller = from - 1;
        int seen = from;
        while (seen < to - 1) {
            if (comparator.compare(partition, items[seen]) >= 0) {
                smaller++;
                ArrayUtils.swap(items, smaller, seen);
            }
            seen++;
        }
        ArrayUtils.swap(items, smaller + 1, to - 1);
        return smaller + 1;
    }

    private E select(int order, int from, int to, E[] items) {
        if (from == to - 1) {
            return items[from];
        }
        final int partition = partition(items, from, to);
        if (order == partition) {
            return items[order];
        } else if (order < partition) {
            return select(order, from, partition, items);
        } else {
            return select(order, partition, to, items);
        }
    }

    @SafeVarargs
    @Override
    public final E select(int order, E... items) {
        if (items.length == 0) {
            return null;
        }
        return select(Math.min(items.length - 1, Math.max(0, order)), 0, items.length, items);
    }

}
