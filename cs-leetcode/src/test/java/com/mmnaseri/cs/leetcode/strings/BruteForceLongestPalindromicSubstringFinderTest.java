package com.mmnaseri.cs.leetcode.strings;

public class BruteForceLongestPalindromicSubstringFinderTest
    extends BaseLongestPalindromicSubstringFinderTest {

  @Override
  protected LongestPalindromicSubstringFinder getInstance() {
    return new BruteForceLongestPalindromicSubstringFinder();
  }
}
