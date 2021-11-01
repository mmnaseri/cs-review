package com.mmnaseri.cs.leetcode.arrays;

public class BruteForceSlidingWindowMaximumTest extends BaseSlidingWindowMaximumTest {

  @Override
  protected SlidingWindowMaximumFinder finder() {
    return new BruteForceSlidingWindowMaximumFinder();
  }
}
