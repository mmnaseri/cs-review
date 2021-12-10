package com.mmnaseri.cs.leetcode.arrays;

public class SlidingWindowMinimumSizeSubarrayFinderTest extends BaseMinimumSizeSubarrayTest {

  @Override
  protected MinimumSizeSubarrayFinder getFinder() {
    return new SlidingWindowMinimumSizeSubarrayFinder();
  }
}