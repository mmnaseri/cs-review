package com.mmnaseri.cs.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

public class LargestUniqueSubstring {

  public int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1) {
      return s.length();
    }
    Map<Character, Integer> positions = new HashMap<>();
    int start = 0;
    int length = 0;
    for (int i = 0; i < s.length(); i ++) {
      if (positions.containsKey(s.charAt(i))) {
        start = Math.max(positions.get(s.charAt(i)) + 1, start);
      }
      positions.put(s.charAt(i), i);
      length = Math.max(length, i - start + 1);
    }
    return length;
  }


}
