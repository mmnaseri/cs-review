package com.mmnaseri.cs.skiena.ch07.s1.impl;

import com.mmnaseri.cs.skiena.ch07.s1.BacktrackEventListener;
import com.mmnaseri.cs.skiena.ch07.s1.BacktrackHandler;
import com.mmnaseri.cs.skiena.ch07.s1.BacktrackingContext;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 11:18 PM)
 */
public class BacktrackHandlerWrapper<E, D> implements BacktrackHandler<E, D>, BacktrackEventListener<E, D> {

    private final BacktrackHandler<E, D> callback;

    public BacktrackHandlerWrapper(BacktrackHandler<E, D> callback) {
        this.callback = callback;
    }

    @Override
    public boolean isSolution(BacktrackingContext<E, D> context) {
        return callback.isSolution(context);
    }

    @Override
    public void process(BacktrackingContext<E, D> context) {
        callback.process(context);
    }

    @Override
    public List<E> generateCandidates(BacktrackingContext<E, D> context) {
        return callback.generateCandidates(context);
    }


    @Override
    public void onBeforeBacktrack(BacktrackingContext<E, D> context) {
        if (callback instanceof BacktrackEventListener) {
            //noinspection unchecked
            ((BacktrackEventListener<E, D>) callback).onBeforeBacktrack(context);
        }
    }

    @Override
    public void onAfterBacktrack(BacktrackingContext<E, D> context) {
        if (callback instanceof BacktrackEventListener) {
            //noinspection unchecked
            ((BacktrackEventListener<E, D>) callback).onAfterBacktrack(context);
        }
    }

}
