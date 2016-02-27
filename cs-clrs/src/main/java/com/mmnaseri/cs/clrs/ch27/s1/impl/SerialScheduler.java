package com.mmnaseri.cs.clrs.ch27.s1.impl;

import com.mmnaseri.cs.clrs.ch27.s1.Action;
import com.mmnaseri.cs.clrs.ch27.s1.Computation;
import com.mmnaseri.cs.clrs.ch27.s1.ComputationResult;
import com.mmnaseri.cs.clrs.ch27.s1.Scheduler;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
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
    public void loop(int from, int to, Action action) {
        if (from > to) {
            return;
        }
        for (int i = from; i <= to; i++) {
            action.perform();
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
