package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

public class LinearMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new LinearMaximumSubArrayFinder();
    }
}