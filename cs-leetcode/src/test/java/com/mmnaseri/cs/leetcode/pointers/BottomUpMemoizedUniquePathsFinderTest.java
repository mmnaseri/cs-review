package com.mmnaseri.cs.leetcode.pointers;

public class BottomUpMemoizedUniquePathsFinderTest extends BaseUniquePathsFinderTest {

  @Override
  protected UniquePathsFinder getInstance() {
    return new BottomUpMemoizedUniquePathsFinder();
  }
}
