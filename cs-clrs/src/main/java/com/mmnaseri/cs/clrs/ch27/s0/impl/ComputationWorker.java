package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.Computation;
import com.mmnaseri.cs.clrs.ch27.s0.ComputationResult;

import java.util.UUID;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ComputationWorker<E> implements Runnable {

    private final SchedulerContext context;
    private final Computation<E> computation;
    private final ComputationResultHolder<E> result;
    private final UUID contextId;

    public ComputationWorker(SchedulerContext context, Computation<E> computation) {
        contextId = context.id();
        this.context = context;
        this.computation = computation;
        this.result = new ComputationResultHolder<>();
    }

    public ComputationResult<E> getResult() {
        return result;
    }

    @Override
    public void run() {
        context.increase(contextId);
        result.set(computation.perform());
        context.decrease(contextId);
    }

    private static class ComputationResultHolder<E> implements ComputationResult<E> {

        private E result;
        private boolean done = false;

        @Override
        public E get() {
            return result;
        }

        @Override
        public boolean done() {
            return done;
        }

        public void set(E result) {
            this.result = result;
            this.done = true;
        }

    }

}
