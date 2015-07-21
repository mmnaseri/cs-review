package com.mmnaseri.cs.clrs.ch9.s3;

import com.mmnaseri.cs.clrs.ch9.Selector;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/12/15, 7:26 PM)
 */
@Quality(Stage.TESTED)
public class LinearSelector<E extends Comparable<E>> implements Selector<E> {

    private final Comparator<E> comparator;

    public LinearSelector(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private int partition(E[] items, int from, int to, int pivot) {
        ArrayUtils.swap(items, pivot, to - 1);
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

    private void sort(E[] items, int[] positions, int from, int to) {
        for (int i = from; i < to; i ++) {
            final E item = items[i];
            final int itemPosition = positions == null ? -1 : positions[i];
            int placement = -1;
            for (int j = from; j < i; j ++) {
                if (comparator.compare(item, items[j]) < 0) {
                    placement = j;
                    break;
                }
            }
            if (placement >= 0) {
                System.arraycopy(items, placement, items, placement + 1, i - placement);
                items[placement] = item;
                if (positions != null) {
                    System.arraycopy(positions, placement, positions, placement + 1, i - placement);
                    positions[placement] = itemPosition;
                }
            }
        }
    }

    private int averagePosition(int lower, int upper) {
        return (int) Math.ceil((lower + upper) / 2.0) - 1;
    }

    @SuppressWarnings("unchecked")
    public int select(E[] items, int[] positions, int from, int to, int order) {
        if (to - from <= 5) {
            sort(items, positions, from, to);
            return order;
        }
        int groups = (int) Math.ceil((to - from) / 5.0);
        final E[] medians = (E[]) Array.newInstance(items.getClass().getComponentType(), groups);
        final int[] medianPositions = new int[groups];
        //sort each group of five, so that their median is immediately reachable
        for (int i = 0; i < groups; i ++) {
            final int lowerBound = from + 5 * i;
            final int upperBound = Math.min(to, from + 5 + 5 * i);
            sort(items, positions, lowerBound, upperBound);
            int median = averagePosition(lowerBound, upperBound);
            medianPositions[i] = median;
            medians[i] = items[median];
        }
        final int medianOfMedians = medianPositions[select(medians, medianPositions, 0, medians.length, averagePosition(0, medians.length))];
        final int pivot = partition(items, from, to, medianOfMedians);
        if (pivot == order) {
            return pivot;
        } else if (order < pivot) {
            return select(items, positions, from, pivot, order);
        } else {
            return select(items, positions, pivot, to, order);
        }
    }

    @SafeVarargs
    @Override
    public final E select(int order, E... items) {
        if (items.length == 0) {
            return null;
        }
        order = Math.min(items.length - 1, order);
        order = Math.max(0, order);
        final int itemIndex = select(items, null, 0, items.length, order);
        return items[itemIndex];
    }

}
