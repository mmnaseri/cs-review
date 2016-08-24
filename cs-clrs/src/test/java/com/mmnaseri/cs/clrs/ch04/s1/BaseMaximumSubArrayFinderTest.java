package com.mmnaseri.cs.clrs.ch04.s1;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/9/15, 11:47 PM)
 */
public abstract class BaseMaximumSubArrayFinderTest {

    protected abstract MaximumSubArrayFinder getFinder();

    @Test
    public void testFindingFromAnEmptyArray() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find();
        assertThat(array, is(nullValue()));
    }

    @Test
    public void testFindingWhenItIsToTheLeft() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find(10, 12, 14, -1, -20, 20);
        assertThat(array, is(notNullValue()));
        assertThat(array.getStart(), is(0));
        assertThat(array.getEnd(), is(2));
        assertThat(array.getSum(), is(10 + 12 + 14));
    }

    @Test
    public void testFindingWhenItIsToTheRight() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find(8, 10, -20, -1, 10, 12, 14);
        assertThat(array, is(notNullValue()));
        assertThat(array.getStart(), is(4));
        assertThat(array.getEnd(), is(6));
        assertThat(array.getSum(), is(10 + 12 + 14));
    }

    @Test
    public void testFindingWhenItCrossesTheMiddle() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find(5, -7, 10, 12, 14, -20, 15);
        assertThat(array, is(notNullValue()));
        assertThat(array.getStart(), is(2));
        assertThat(array.getEnd(), is(4));
        assertThat(array.getSum(), is(10 + 12 + 14));
    }

    @Test
    public void testFindingWhenThereAreNoNegatives() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find(1, 2, 3, 4, 5);
        assertThat(array, is(notNullValue()));
        assertThat(array.getStart(), is(0));
        assertThat(array.getEnd(), is(4));
        assertThat(array.getSum(), is(1 + 2 + 3 + 4 + 5));
    }

    @Test
    public void testFindingWhenAllAreNegative() throws Exception {
        final MaximumSubArrayFinder finder = getFinder();
        final SubArray array = finder.find(-1, -2, -3);
        assertThat(array, is(notNullValue()));
        assertThat(array.getStart(), is(0));
        assertThat(array.getEnd(), is(0));
        assertThat(array.getSum(), is(-1));
    }

}
