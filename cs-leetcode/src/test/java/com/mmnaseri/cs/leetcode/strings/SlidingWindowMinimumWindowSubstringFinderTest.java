package com.mmnaseri.cs.leetcode.strings;

public class SlidingWindowMinimumWindowSubstringFinderTest extends BaseMinimumWindowSubstringFinderTest {

  @Override
  protected MinimumWindowSubstringFinder getFinder() {
    return new SlidingWindowMinimumWindowSubstringFinder();
  }
}