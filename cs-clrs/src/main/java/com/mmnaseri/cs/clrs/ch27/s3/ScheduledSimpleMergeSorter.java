package com.mmnaseri.cs.clrs.ch27.s3;

import com.mmnaseri.cs.clrs.ch02.s3.MergeSorter;
import com.mmnaseri.cs.clrs.ch27.s0.Action;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (3/13/16, 6:48 PM)
 */
@Quality(Stage.TESTED)
public class ScheduledSimpleMergeSorter<E extends Comparable<E>> extends MergeSorter<E> {

    private final SchedulerFactory factory;

    public ScheduledSimpleMergeSorter(Comparator<E> comparator, SchedulerFactory factory) {
        super(comparator);
        this.factory = factory;
    }

    @Override
    protected void sort(final E[] items, final int from, int to) {
        if (to - from < 2) {
            return;
        }
        final int mid = from + (to - from) / 2;
        final Scheduler scheduler = factory.getScheduler();
        scheduler.spawn(new Action() {
            @Override
            public void perform() {
                sort(items, from, mid);
            }
        });
        sort(items, mid, to);
        scheduler.syncAndEnd();
        merge(items, from, mid, to);
    }

}
