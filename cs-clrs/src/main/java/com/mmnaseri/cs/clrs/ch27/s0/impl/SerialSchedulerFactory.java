package com.mmnaseri.cs.clrs.ch27.s0.impl;

import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
public class SerialSchedulerFactory implements SchedulerFactory {
    @Override
    public Scheduler getScheduler() {
        return new SerialScheduler();
    }
}
