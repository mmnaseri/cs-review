package com.mmnaseri.cs.leetcode.arrays;

public class DequeSlidingWindowMaximumFinderTest extends BaseSlidingWindowMaximumTest {

  @Override
  protected SlidingWindowMaximumFinder finder() {
    return new DequeSlidingWindowMaximumFinder();
  }
}