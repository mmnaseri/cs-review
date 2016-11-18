package com.mmnaseri.cs.skiena.ch04.s8.impl;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch04.s8.Merger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (11/17/16, 3:27 PM)
 */
@Quality(Stage.TESTED)
public class NaiveMerger<E> implements Merger<E> {

    private final Comparator<E> comparator;

    public NaiveMerger(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SafeVarargs
    @Override
    public final List<E> merge(List<E>... lists) {
        final List<E> result = new ArrayList<>();
        while (true) {
            int which = -1;
            for (int i = 0; i < lists.length; i++) {
                final List<E> list = lists[i];
                if (!list.isEmpty() && (which == -1 || comparator.compare(lists[which].get(0), list.get(0)) > 0)) {
                    which = i;
                }
            }
            if (which >= 0) {
                result.add(lists[which].remove(0));
            } else {
                break;
            }
        }
        return result;
    }

}
