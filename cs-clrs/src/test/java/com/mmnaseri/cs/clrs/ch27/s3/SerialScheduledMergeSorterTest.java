package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 08:06)
 */
public class SerialScheduledMergeSorterTest extends AbstractScheduledMergeSorterTest {

    @Override
    protected SchedulerFactory getSchedulerFactory() {
        return new SerialSchedulerFactory();
    }

}
