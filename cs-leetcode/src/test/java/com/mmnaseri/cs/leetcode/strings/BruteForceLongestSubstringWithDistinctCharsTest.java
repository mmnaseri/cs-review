package com.mmnaseri.cs.leetcode.strings;

public class BruteForceLongestSubstringWithDistinctCharsTest
    extends BaseLongestSubstringWithDistinctCharsTest {

  @Override
  protected LongestSubstringWithDistinctChars getSolver() {
    return new BruteForceLongestSubstringWithDistinctChars();
  }
}
