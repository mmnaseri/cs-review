package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch04.s8.Merger;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (11/17/16, 3:15 PM)
 */
@Quality(Stage.TESTED)
public class ComparingHeapMerger<E> implements Merger<E> {

    private final Comparator<E> comparator;

    public ComparingHeapMerger(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Complexity(value = "O(n.log(k))", explanation = "See HeapMerger")
    @SafeVarargs
    @Override
    public final List<E> merge(final List<E>... lists) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>(lists.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                return comparator.compare(lists[first].get(0), lists[second].get(0));
            }
        });
        final List<E> result = new LinkedList<>();
        for (int i = 0; i < lists.length; i++) {
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            final Integer index = queue.remove();
            final E item = lists[index].remove(0);
            result.add(item);
            if (!lists[index].isEmpty()) {
                queue.add(index);
            }
        }
        return result;
    }

}
