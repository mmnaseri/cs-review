package com.mmnaseri.cs.clrs.ch02.s3;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class PairSumFinderTest {

    @Test
    public void testLookingForNonExistentSum() throws Exception {
        final PairSumFinder finder = new PairSumFinder();
        assertThat(finder.findPair(0, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findPair(20, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findPair(Integer.MIN_VALUE, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findPair(Integer.MAX_VALUE, 1, 2, 3, 4, 5, 6), is(nullValue()));
    }

    @Test
    public void testLookingForNegativeSum() throws Exception {
        final PairSumFinder finder = new PairSumFinder();
        assertThat(finder.findPair(-20, -30, 2, 3, 4, 10, 5), is(new Pair(-30, 10)));
        assertThat(finder.findPair(-30, 5, 6, 7, -36, -37), is(new Pair(7, -37)));
    }

    @Test
    public void testLookingForPositiveSum() throws Exception {
        final PairSumFinder finder = new PairSumFinder();
        assertThat(finder.findPair(20, 5, 10, 15, 20), is(new Pair(5, 15)));
        assertThat(finder.findPair(20, 5, 7, 10, 15, 20, 13), is(new Pair(5, 15)));
    }

    @Test
    public void testLookingForZero() throws Exception {
        final PairSumFinder finder = new PairSumFinder();
        assertThat(finder.findPair(0, 5, 6, -5, -6, -7), is(new Pair(-6, 6)));
    }
}