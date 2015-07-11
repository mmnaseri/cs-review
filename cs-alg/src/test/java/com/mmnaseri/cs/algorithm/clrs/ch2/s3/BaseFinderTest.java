package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import com.mmnaseri.cs.algorithm.common.Finder;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/11/15, 1:25 PM)
 */
public abstract class BaseFinderTest {

    protected abstract Finder<Integer> getFinder();

    @Test
    public void testFindingItemsAtTheBeginning() throws Exception {
        assertThat(getFinder().find(1, 1, 2, 3, 4, 5), is(0));
    }

    @Test
    public void testFindingItemsAtTheEnd() throws Exception {
        assertThat(getFinder().find(5, 1, 2, 3, 4, 5), is(4));
    }

    @Test
    public void testFindingFromAnEmptyList() throws Exception {
        assertThat(getFinder().find(0), is(-1));
    }

    @Test
    public void testFindingItemInTheMiddle() throws Exception {
        assertThat(getFinder().find(5, 1, 3, 5, 7, 9), is(2));
    }

    @Test
    public void testFindingItemThatIsNotThere() throws Exception {
        assertThat(getFinder().find(-1, 1, 2, 3, 4), is(-1));
    }

    @Test
    public void testFindingItemWhenListContainsInfinity() throws Exception {
        assertThat(getFinder().find(5, Integer.MIN_VALUE, 1, 2, 3, 5, Integer.MAX_VALUE), is(4));
    }

}
