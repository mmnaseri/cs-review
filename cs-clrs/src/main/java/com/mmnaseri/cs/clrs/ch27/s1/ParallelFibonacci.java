package com.mmnaseri.cs.clrs.ch27.s1;

import com.mmnaseri.cs.clrs.ch27.s1.impl.DefaultScheduler;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ParallelFibonacci {

    private final Scheduler scheduler = DefaultScheduler.initialize(this);

    public long get(final int index) {
        if (index <= 1) {
            return 1;
        }
        //spawn a new calculation that might happen in the future
        final ComputationResult<Long> x = scheduler.spawn(new Computation<Long>() {
            @Override
            public Long perform() {
                return new ParallelFibonacci().get(index - 1);
            }
        });
        //start calculating for `n-2` (which could end in the current thread)
        final long y = new ParallelFibonacci().get(index - 2);
        //sync the spawned children AND end the executor
        scheduler.syncAndEnd();
        //return the sum of the two
        return x.get() + y;
    }

}
