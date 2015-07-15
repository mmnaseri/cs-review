package com.mmnaseri.cs.clrs.ch4.s1;

public class BruteForceMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new BruteForceMaximumSubArrayFinder();
    }

}