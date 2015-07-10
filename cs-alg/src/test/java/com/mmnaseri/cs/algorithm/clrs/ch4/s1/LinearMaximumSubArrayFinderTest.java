package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

import com.mmnaseri.cs.algorithm.clrs.BaseMaximumSubArrayFinderTest;
import org.testng.annotations.Test;

@Test(enabled = false)
public class LinearMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new LinearMaximumSubArrayFinder();
    }
}