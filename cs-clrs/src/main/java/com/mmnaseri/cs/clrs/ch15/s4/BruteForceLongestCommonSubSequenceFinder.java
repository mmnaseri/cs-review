package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
@Quality(Stage.TESTED)
public class BruteForceLongestCommonSubSequenceFinder<E> implements LongestCommonSubSequenceFinder<E> {

    @Override
    public List<E> find(List<E> first, List<E> second) {
        if (first == null || second == null) {
            return null;
        }
        final List<List<E>> subSequences = SequenceUtils.subSequences(first);
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

}
