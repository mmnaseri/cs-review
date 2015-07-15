package com.mmnaseri.cs.clrs.ch4.s1;

public class LinearMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new LinearMaximumSubArrayFinder();
    }
}