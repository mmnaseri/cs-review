package com.mmnaseri.cs.leetcode.strings;

public class MemoizedLongestPalindromicSubstringFinderTest
    extends BaseLongestPalindromicSubstringFinderTest {

  @Override
  protected LongestPalindromicSubstringFinder getInstance() {
    return new MemoizedLongestPalindromicSubstringFinder();
  }
}
