package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ContextAwareParallelSchedulerFactory;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (3/13/16, 6:56 PM)
 */
public class ParallelScheduledSimpleMergeSorterTest extends ScheduledSimpleMergeSorterTest {

    @Override
    protected SchedulerFactory getSchedulerFactory() {
        return new ContextAwareParallelSchedulerFactory();
    }

}
