package com.mmnaseri.cs.algorithm.clrs.ch9.s2;

import com.mmnaseri.cs.algorithm.clrs.ch9.Selector;
import com.mmnaseri.cs.algorithm.common.ArrayUtils;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 5:47 PM)
 */
public class RandomizedSelector<E extends Comparable<E>> implements Selector<E> {

    private final Comparator<E> comparator;

    public RandomizedSelector(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected int partition(E[] items, int from, int to) {
        final int index = new Random().nextInt(to - from) + from;
        ArrayUtils.swap(items, index, to - 1);
        final E partition = items[to - 1];
        int smaller = from - 1;
        int seen = from;
        while (seen < to - 1) {
            if (comparator.compare(partition, items[seen]) >= 0) {
                smaller ++;
                ArrayUtils.swap(items, smaller, seen);
            }
            seen ++;
        }
        ArrayUtils.swap(items, smaller + 1, to - 1);
        return smaller + 1;
    }

    private E select(int order, int from, int to, E[] items) {
        if (from == to - 1) {
            return items[from];
        }
        final int partition = partition(items, from, to);
        final int pivot = partition - from + 1;
        if (order == partition) {
            return items[order];
        } else if (order < pivot) {
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
        return select(Math.min(items.length, Math.max(0, order)), 0, items.length, items);
    }

}
