package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
public class SerialScheduler implements Scheduler {

    SerialScheduler() {
    }

    @Override
    public void spawn(Action action) {
        action.perform();
    }

    @Override
    public <E> ComputationResult<E> spawn(Computation<E> computation) {
        return new StaticComputationResultHolder<>(computation.perform());
    }

    @Override
    public void loop(int from, int to, LoopStep step) {
        if (from >= to) {
            return;
        }
        for (int i = from; i < to; i++) {
            step.perform(i);
        }
    }

    @Override
    public void sync() {
    }

    @Override
    public void end() {
    }

    @Override
    public void syncAndEnd() {
    }

    private static class StaticComputationResultHolder<E> implements ComputationResult<E> {

        private final E result;

        private StaticComputationResultHolder(E result) {
            this.result = result;
        }

        @Override
        public E get() {
            return result;
        }

        @Override
        public boolean done() {
            return true;
        }

    }

}
