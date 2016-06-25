package com.mmnaseri.cs.clrs.ch27.s0.impl;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class SimpleParallelScheduler extends AbstractParallelScheduler {

    SimpleParallelScheduler() {
        super();
    }

    @Override
    protected SchedulerContext createSchedulerContext() {
        return new SimpleSchedulerContext();
    }

}
