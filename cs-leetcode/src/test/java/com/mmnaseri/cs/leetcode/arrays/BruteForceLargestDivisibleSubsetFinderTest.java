package com.mmnaseri.cs.leetcode.arrays;

public class BruteForceLargestDivisibleSubsetFinderTest
    extends BaseLargestDivisibleSubsetFinderTest {

  @Override
  protected LargestDivisibleSubsetFinder getInstance() {
    return new BruteForceLargestDivisibleSubsetFinder();
  }
}
