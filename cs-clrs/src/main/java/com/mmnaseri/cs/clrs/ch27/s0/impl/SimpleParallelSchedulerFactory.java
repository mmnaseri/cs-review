package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 07:57)
 */
public class SimpleParallelSchedulerFactory implements SchedulerFactory {

    @Override
    public Scheduler getScheduler() {
        return new SimpleParallelScheduler();
    }

}
