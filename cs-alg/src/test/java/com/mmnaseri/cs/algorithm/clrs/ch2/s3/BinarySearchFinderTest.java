package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BinarySearchFinderTest {

    private BinarySearchFinder<Integer> finder;

    @BeforeMethod
    public void setUp() throws Exception {
        finder = new BinarySearchFinder<>();
    }

    @Test
    public void testFindingItemsAtTheBeginning() throws Exception {
        assertThat(finder.find(1, 1, 2, 3, 4, 5), is(0));
    }

    @Test
    public void testFindingItemsAtTheEnd() throws Exception {
        assertThat(finder.find(5, 1, 2, 3, 4, 5), is(4));
    }

    @Test
    public void testFindingFromAnEmptyList() throws Exception {
        assertThat(finder.find(0), is(-1));
    }

    @Test
    public void testFindingItemInTheMiddle() throws Exception {
        assertThat(finder.find(5, 1, 3, 5, 7, 9), is(2));
    }

    @Test
    public void testFindingItemThatIsNotThere() throws Exception {
        assertThat(finder.find(-1, 1, 2, 3, 4), is(-1));
    }
}