package com.mmnaseri.cs.leetcode.strings;

public class SlidingWindowLongestSubstringWithDistinctCharsTest extends BaseLongestSubstringWithDistinctCharsTest {

  @Override
  protected LongestSubstringWithDistinctChars getSolver() {
    return new SlidingWindowLongestSubstringWithDistinctChars();
  }
}