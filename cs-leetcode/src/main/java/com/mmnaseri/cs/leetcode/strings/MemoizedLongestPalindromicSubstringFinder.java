package com.mmnaseri.cs.leetcode.strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemoizedLongestPalindromicSubstringFinder
    implements LongestPalindromicSubstringFinder {

  @Override
  public String longestPalindrome(String s) {
    if (s.length() <= 1) {
      return s;
    }
    Range max = new Range(0, 0);
    Map<Range, Boolean> memory = new HashMap<>();
    for (int start = 0; start < s.length(); start++) {
      for (int end = start + 1; end < s.length() + 1; end++) {
        Range range = new Range(start, end);
        if (isPalindrome(s, range, memory) && range.length() > max.length()) {
          max = range;
        }
      }
    }
    return s.substring(max.start, max.end);
  }

  private static boolean isPalindrome(String s, Range range, Map<Range, Boolean> memory) {
    if (memory.containsKey(range)) {
      return memory.get(range);
    }
    boolean answer =
        range.end - range.start <= 1
            || (s.charAt(range.start) == s.charAt(range.end - 1)
                && isPalindrome(s, range.shrink(), memory));
    memory.put(range, answer);
    return answer;
  }

  private static class Range {
    int start;
    int end;

    public Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int length() {
      return end - start;
    }

    public Range shrink() {
      return new Range(start + 1, end - 1);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Range range = (Range) o;
      return start == range.start && end == range.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }
}
