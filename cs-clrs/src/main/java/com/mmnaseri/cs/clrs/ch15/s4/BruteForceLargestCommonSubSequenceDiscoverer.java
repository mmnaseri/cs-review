package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.Quality;
import com.mmnaseri.cs.qa.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
@Quality(Stage.TESTED)
public class BruteForceLargestCommonSubSequenceDiscoverer<E> implements LargestCommonSubSequenceDiscoverer<E> {

    @Override
    public List<E> find(List<E> first, List<E> second) {
        if (first == null || second == null) {
            return null;
        }
        final List<List<E>> subSequences = subSequences(first);
        Collections.sort(subSequences, new Comparator<List<E>>() {
            @Override
            public int compare(List<E> o1, List<E> o2) {
                return Integer.compare(o2.size(), o1.size());
            }
        });
        for (List<E> subSequence : subSequences) {
            if (subSequence.isEmpty()) {
                continue;
            }
            if (has(subSequence, second)) {
                return subSequence;
            }
        }
        return Collections.emptyList();
    }

    private static <E> boolean has(List<E> needle, List<E> haystack) {
        int i = 0;
        int j = 0;
        while (i < needle.size() && j < haystack.size()) {
            if (needle.get(i).equals(haystack.get(j))) {
                i ++;
            }
            j ++;
        }
        return i >= needle.size();
    }

    private static <E> List<List<E>> subSequences(List<E> sequence) {
        final ArrayList<List<E>> state = new ArrayList<>();
        state.add(new ArrayList<E>());
        return subSequences(sequence, state);
    }

    private static <E> List<List<E>> subSequences(List<E> sequence, List<List<E>> state) {
        if (sequence.isEmpty()) {
            return state;
        }
        final E first = sequence.get(0);
        final List<E> rest;
        if (sequence.size() > 1) {
            rest = sequence.subList(1, sequence.size());
        } else {
            rest = Collections.emptyList();
        }
        final ArrayList<List<E>> next = new ArrayList<>();
        for (List<E> list : state) {
            final ArrayList<E> with = new ArrayList<>(list);
            final ArrayList<E> without = new ArrayList<>(list);
            with.add(first);
            next.add(with);
            next.add(without);
        }
        return subSequences(rest, next);
    }

}
