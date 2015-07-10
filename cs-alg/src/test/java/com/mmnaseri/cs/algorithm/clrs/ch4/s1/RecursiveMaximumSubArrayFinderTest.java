package com.mmnaseri.cs.algorithm.clrs.ch4.s1;

import com.mmnaseri.cs.algorithm.clrs.BaseMaximumSubArrayFinderTest;
import org.testng.annotations.Test;

@Test(enabled = false)
public class RecursiveMaximumSubArrayFinderTest extends BaseMaximumSubArrayFinderTest {

    @Override
    protected MaximumSubArrayFinder getFinder() {
        return new RecursiveMaximumSubArrayFinder();
    }
}