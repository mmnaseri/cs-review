package com.mmnaseri.cs.clrs.ch04.s1;

public class RecursiveMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new RecursiveMaximumSubArrayFinder();
    }
}