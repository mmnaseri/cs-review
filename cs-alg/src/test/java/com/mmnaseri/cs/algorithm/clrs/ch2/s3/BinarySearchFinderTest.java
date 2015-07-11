package com.mmnaseri.cs.algorithm.clrs.ch2.s3;

import com.mmnaseri.cs.algorithm.common.Finder;

public class BinarySearchFinderTest extends BaseFinderTest {

    @Override
    protected Finder<Integer> getFinder() {
        return new BinarySearchFinder<>();
    }

}