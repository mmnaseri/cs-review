package com.mmnaseri.cs.leetcode.pointers;

public class MemoizedUniquePathsFinderTest extends BaseUniquePathsFinderTest {

  @Override
  protected UniquePathsFinder getInstance() {
    return new MemoizedUniquePathsFinder();
  }
}
