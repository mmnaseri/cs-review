package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ContextAwareParallelSchedulerFactory;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 14:01)
 */
public class ParallelScheduledMergeSorterTest extends AbstractScheduledMergeSorterTest {

    @Override
    protected SchedulerFactory getSchedulerFactory() {
        return new ContextAwareParallelSchedulerFactory();
    }

}