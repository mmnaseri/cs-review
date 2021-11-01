package com.mmnaseri.cs.leetcode.strings;

public class BruteForceMinimumWindowSubstringFinderTest extends BaseMinimumWindowSubstringFinderTest {

  @Override
  protected MinimumWindowSubstringFinder getFinder() {
    return new BruteForceMinimumWindowSubstringFinder();
  }
}
