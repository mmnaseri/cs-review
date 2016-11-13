package com.mmnaseri.cs.skiena.ch07.s1.impl;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;
import com.mmnaseri.cs.skiena.ch07.s1.BacktrackHandler;
import com.mmnaseri.cs.skiena.ch07.s1.Backtracker;
import com.mmnaseri.cs.skiena.ch07.s1.BacktrackingContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:20 PM)
 */
@Quality(Stage.UNTESTED)
public class DefaultBacktracker<E, D> implements Backtracker<E, D> {

    @Override
    public void backtrack(BacktrackHandler<E, D> callback) {
        backtrack(null, callback);
    }

    @Override
    public void backtrack(D data, BacktrackHandler<E, D> handler) {
        final BacktrackingContext<E, D> context = new DefaultBacktrackingContext<>(data, Collections.<E>emptyList());
        backtrack(context, new BacktrackHandlerWrapper<>(handler));
    }

    private void backtrack(BacktrackingContext<E, D> context, BacktrackHandlerWrapper<E, D> handler) {
        if (handler.isSolution(context)) {
            handler.process(context);
            return;
        }
        final List<E> candidates = handler.generateCandidates(context);
        for (E candidate : candidates) {
            final List<E> items = new ArrayList<>(context.current());
            items.add(candidate);
            handler.onBeforeBacktrack(context);
            final DefaultBacktrackingContext<E, D> currentContext = new DefaultBacktrackingContext<>(context.data(), items);
            backtrack(currentContext, handler);
            handler.onAfterBacktrack(context);
            if (currentContext.isStopped()) {
                context.stop();
                return;
            }
        }
    }

}
