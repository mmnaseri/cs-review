package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch02.s3.MergeSorterTest;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.common.Sorter;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/15, 08:05)
 */
public abstract class AbstractScheduledMergeSorterTest extends MergeSorterTest {

    @Override
    protected Sorter<Integer> getAscendingSorter() {
        return new ScheduledMergeSorter<>(NATURAL_COMPARATOR, getSchedulerFactory());
    }

    protected abstract SchedulerFactory getSchedulerFactory();

}