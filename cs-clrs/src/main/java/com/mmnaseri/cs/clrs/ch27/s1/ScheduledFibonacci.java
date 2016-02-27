package com.mmnaseri.cs.clrs.ch27.s1;

import com.mmnaseri.cs.clrs.ch27.s0.Computation;
import com.mmnaseri.cs.clrs.ch27.s0.ComputationResult;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
@Quality(Stage.TESTED)
public class ScheduledFibonacci {

    private final SchedulerFactory schedulerFactory;
    private final Scheduler scheduler;

    public ScheduledFibonacci(SchedulerFactory schedulerFactory) {
        this.schedulerFactory = schedulerFactory;
        scheduler = schedulerFactory.getScheduler();
    }

    public long get(final int index) {
        //if the index is less than 1, finish calculation
        if (index <= 1) {
            return 1;
        }
        //spawn a new calculation that might happen in the future
        final ComputationResult<Long> x = scheduler.spawn(new Computation<Long>() {
            @Override
            public Long perform() {
                return new ScheduledFibonacci(schedulerFactory).get(index - 1);
            }
        });
        //start calculating for `n-2` (which could end in the current thread)
        final long y = new ScheduledFibonacci(schedulerFactory).get(index - 2);
        //sync the spawned children AND end the executor
        scheduler.syncAndEnd();
        //return the sum of the two
        return x.get() + y;
    }

}
