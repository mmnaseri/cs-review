package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch04.s8.Merger;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 12:58 AM)
 */
@Quality(Stage.TESTED)
public class HeapMerger<E> implements Merger<E> {

    private final Comparator<E> comparator;

    public HeapMerger(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Complexity(value = "O(n.log(k))", explanation = "`n` is the total number of items in the resulting list, and `k` is the number of lists (log(k) is based on the assumption that we are using a good implementation of a heap)")
    @SafeVarargs
    @Override
    public final List<E> merge(List<E>... lists) {
        final Queue<ListIndicator<E>> queue = new PriorityQueue<>(lists.length, new ListIndicatorComparator<>(comparator));
        final List<E> result = new LinkedList<>();
        for (List<E> list : lists) { //O(k.log(k))
            if (!list.isEmpty()) {
                queue.add(new ListIndicator<>(list, list.remove(0))); //O(log(k))
            }
        }
        while (!queue.isEmpty()) { //O(n(1+2.log(k))) = O(n.log(k))
            final ListIndicator<E> next = queue.remove(); //O(log(k))
            result.add(next.getItem()); //O(1)
            if (!next.getList().isEmpty()) {
                queue.add(new ListIndicator<>(next.getList(), next.getList().remove(0))); //O(log(k))
            }
        }
        return result;
    }

    private static class ListIndicator<E> {

        private final List<E> list;
        private final E item;

        private ListIndicator(List<E> list, E item) {
            this.list = list;
            this.item = item;
        }

        private List<E> getList() {
            return list;
        }

        private E getItem() {
            return item;
        }

    }

    private static class ListIndicatorComparator<E> implements Comparator<ListIndicator<E>> {

        private final Comparator<E> comparator;

        private ListIndicatorComparator(Comparator<E> comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(ListIndicator<E> first, ListIndicator<E> second) {
            return comparator.compare(first.getItem(), second.getItem());
        }

    }

}
