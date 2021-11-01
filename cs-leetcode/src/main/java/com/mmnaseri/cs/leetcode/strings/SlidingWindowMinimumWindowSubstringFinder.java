package com.mmnaseri.cs.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowMinimumWindowSubstringFinder implements MinimumWindowSubstringFinder {

  @Override
  public String minWindow(String haystack, String needle) {
    Map<Character, Integer> needleCharmap = charmap(needle);
    Map<Character, Integer> windowCharmap = new HashMap<>();
    int minStart = 0;
    int minEnd = Integer.MAX_VALUE;
    int start = 0;
    for (int i = 0; i < haystack.length(); i++) {
      windowCharmap.put(haystack.charAt(i), windowCharmap.getOrDefault(haystack.charAt(i), 0) + 1);
      while (contains(needleCharmap, windowCharmap)) {
        if (i - start < minEnd - minStart) {
          minEnd = i + 1;
          minStart = start;
        }
        windowCharmap.put(haystack.charAt(start), windowCharmap.get(haystack.charAt(start)) - 1);
        if (windowCharmap.get(haystack.charAt(start)) == 0) {
          windowCharmap.remove(haystack.charAt(start));
        }
        start ++;
      }
    }
    return minEnd > haystack.length() ? "" : haystack.substring(minStart, minEnd);
  }

  private Map<Character, Integer> charmap(String str) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : str.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }

  private boolean contains(Map<Character, Integer> needle, Map<Character, Integer> haystack) {
    for (Map.Entry<Character, Integer> entry : needle.entrySet()) {
      if (!haystack.containsKey(entry.getKey())) {
        return false;
      }
      if (haystack.get(entry.getKey()) < entry.getValue()) {
        return false;
      }
    }
    return true;
  }
}
