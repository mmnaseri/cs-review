package com.mmnaseri.cs.leetcode.arrays;

public class BstSlidingWindowMaximumFinderTest extends BaseSlidingWindowMaximumTest {

  @Override
  protected SlidingWindowMaximumFinder finder() {
    return new BstSlidingWindowMaximumFinder();
  }
}