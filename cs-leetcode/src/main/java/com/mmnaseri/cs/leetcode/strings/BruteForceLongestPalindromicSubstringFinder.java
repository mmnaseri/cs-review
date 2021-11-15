package com.mmnaseri.cs.leetcode.strings;

public class BruteForceLongestPalindromicSubstringFinder
    implements LongestPalindromicSubstringFinder {

  @Override
  public String longestPalindrome(String s) {
    if (s.length() <= 1) {
      return s;
    }
    int maxStart = 0;
    int maxEnd = 0;
    for (int start = 0; start < s.length(); start++) {
      for (int end = start; end < s.length() + 1; end++) {
        if (isPalindrome(s, start, end) && maxEnd - maxStart < end - start) {
          maxStart = start;
          maxEnd = end;
        }
      }
    }
    return s.substring(maxStart, maxEnd);
  }

  private static boolean isPalindrome(String s, int start, int end) {
    return end - start <= 1
        || (s.charAt(start) == s.charAt(end - 1) && isPalindrome(s, start + 1, end - 1));
  }
}
