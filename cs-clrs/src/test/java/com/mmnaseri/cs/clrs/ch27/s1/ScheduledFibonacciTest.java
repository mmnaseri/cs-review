package com.mmnaseri.cs.clrs.ch27.s1;

import com.mmnaseri.cs.clrs.ch27.s0.impl.ParallelSchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ScheduledFibonacciTest {

    @Test
    public void testParallel() throws Exception {
        final ScheduledFibonacci fibonacci = new ScheduledFibonacci(new ParallelSchedulerFactory());
        long[] results = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        for (int i = 0; i < 11; i++) {
            assertThat(fibonacci.get(i), is(results[i]));
        }
    }

    @Test
    public void testSerial() throws Exception {
        final ScheduledFibonacci fibonacci = new ScheduledFibonacci(new SerialSchedulerFactory());
        long[] results = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        for (int i = 0; i < 11; i++) {
            assertThat(fibonacci.get(i), is(results[i]));
        }
    }

    @Test(dependsOnMethods = {"testParallel", "testSerial"})
    public void testParallelizationIntegrity() throws Exception {
        final ScheduledFibonacci first = new ScheduledFibonacci(new ParallelSchedulerFactory());
        final ScheduledFibonacci second = new ScheduledFibonacci(new SerialSchedulerFactory());
        for (int i = 0; i < 10; i++) {
            assertThat(first.get(i), is(second.get(i)));
        }
    }

}