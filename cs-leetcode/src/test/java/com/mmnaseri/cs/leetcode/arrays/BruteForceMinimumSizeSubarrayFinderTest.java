package com.mmnaseri.cs.leetcode.arrays;

public class BruteForceMinimumSizeSubarrayFinderTest extends BaseMinimumSizeSubarrayTest {

  @Override
  protected MinimumSizeSubarrayFinder getFinder() {
    return new BruteForceMinimumSizeSubarrayFinder();
  }
}
