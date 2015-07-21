package com.mmnaseri.cs.clrs.ch02.s3;

import com.mmnaseri.cs.clrs.common.Finder;

public class LinearFinderTest extends BaseFinderTest {

    @Override
    protected Finder<Integer> getFinder() {
        return new LinearFinder<>();
    }

}