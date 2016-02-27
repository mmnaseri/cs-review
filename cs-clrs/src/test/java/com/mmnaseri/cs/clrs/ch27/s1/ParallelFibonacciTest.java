package com.mmnaseri.cs.clrs.ch27.s1;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class ParallelFibonacciTest {

    @Test
    public void testFibonacci() throws Exception {
        final ParallelFibonacci fibonacci = new ParallelFibonacci();
        long[] results = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        for (int i = 0; i < 11; i++) {
            assertThat(fibonacci.get(i), is(results[i]));
        }
    }

}