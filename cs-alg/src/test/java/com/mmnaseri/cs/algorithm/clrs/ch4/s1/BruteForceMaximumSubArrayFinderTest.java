package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

import com.mmnaseri.cs.algorithm.clrs.BaseMaximumSubArrayFinderTest;

public class BruteForceMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new BruteForceMaximumSubArrayFinder();
    }

}