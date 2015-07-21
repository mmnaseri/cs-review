package com.mmnaseri.cs.clrs.ch04.s1;

public class LinearMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new LinearMaximumSubArrayFinder();
    }
}