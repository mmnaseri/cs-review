package com.mmnaseri.cs.skiena.ch07.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch07.s1.impl.DefaultBacktracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:37 PM)
 */
@Quality(Stage.UNTESTED)
public class PermutationGenerator<E> {

    private final Backtracker<E, Void> backtracker;

    public PermutationGenerator() {
        backtracker = new DefaultBacktracker<>();
    }

    public Set<List<E>> generate(final Set<E> set) {
        final Set<List<E>> permutations = new HashSet<>();
        backtracker.backtrack(new BacktrackHandler<E, Void>() {
            @Override
            public boolean isSolution(BacktrackingContext<E, Void> context) {
                return context.size() == set.size();
            }

            @Override
            public void process(BacktrackingContext<E, Void> context) {
                permutations.add(context.current());
            }

            @Override
            public List<E> generateCandidates(BacktrackingContext<E, Void> context) {
                final List<E> candidates = new ArrayList<>(set);
                candidates.removeAll(context.current());
                return candidates;
            }
        });
        return permutations;
    }

}
