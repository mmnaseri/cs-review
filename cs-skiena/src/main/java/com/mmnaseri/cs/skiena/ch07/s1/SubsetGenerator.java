package com.mmnaseri.cs.skiena.ch07.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch07.s1.impl.DefaultBacktracker;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:29 PM)
 */
@Quality(Stage.UNTESTED)
public class SubsetGenerator<E> {

    private final Backtracker<E, Void> backtracker;

    public SubsetGenerator() {
        backtracker = new DefaultBacktracker<>();
    }

    public Set<Set<E>> generate(final Set<E> set) {
        final List<E> items = new ArrayList<>(set);
        final Set<Set<E>> result = new HashSet<>();
        backtracker.backtrack(new BacktrackHandler<E, Void>() {
            @Override
            public boolean isSolution(BacktrackingContext<E, Void> context) {
                return context.size() == set.size();
            }

            @Override
            public void process(BacktrackingContext<E, Void> context) {
                final Set<E> answer = new HashSet<>();
                for (E item : context.current()) {
                    if (item != null) {
                        answer.add(item);
                    }
                }
                result.add(answer);
            }

            @Override
            public List<E> generateCandidates(BacktrackingContext<E, Void> context) {
                return Arrays.asList(items.get(context.size()), null);
            }
        });
        return result;
    }

}
