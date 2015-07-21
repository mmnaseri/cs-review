package com.mmnaseri.cs.clrs.ch02.s3;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class CoupleSumFinderTest {

    @Test
    public void testLookingForNonExistentSum() throws Exception {
        final CoupleSumFinder finder = new CoupleSumFinder();
        assertThat(finder.findCouple(0, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findCouple(20, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findCouple(Integer.MIN_VALUE, 1, 2, 3, 4, 5, 6), is(nullValue()));
        assertThat(finder.findCouple(Integer.MAX_VALUE, 1, 2, 3, 4, 5, 6), is(nullValue()));
    }

    @Test
    public void testLookingForNegativeSum() throws Exception {
        final CoupleSumFinder finder = new CoupleSumFinder();
        assertThat(finder.findCouple(-20, -30, 2, 3, 4, 10, 5), is(new Couple(-30, 10)));
        assertThat(finder.findCouple(-30, 5, 6, 7, -36, -37), is(new Couple(7, -37)));
    }

    @Test
    public void testLookingForPositiveSum() throws Exception {
        final CoupleSumFinder finder = new CoupleSumFinder();
        assertThat(finder.findCouple(20, 5, 10, 15, 20), is(new Couple(5, 15)));
        assertThat(finder.findCouple(20, 5, 7, 10, 15, 20, 13), is(new Couple(5, 15)));
    }

    @Test
    public void testLookingForZero() throws Exception {
        final CoupleSumFinder finder = new CoupleSumFinder();
        assertThat(finder.findCouple(0, 5, 6, -5, -6, -7), is(new Couple(-6, 6)));
    }
}