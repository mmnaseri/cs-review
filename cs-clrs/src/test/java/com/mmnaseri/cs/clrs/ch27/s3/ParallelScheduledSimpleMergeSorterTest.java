package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ParallelSchedulerFactory;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (3/13/16, 6:56 PM)
 */
public class ParallelScheduledSimpleMergeSorterTest extends ScheduledSimpleMergeSorterTest {

    @Override
    protected SchedulerFactory getSchedulerFactory() {
        return new ParallelSchedulerFactory();
    }

}
