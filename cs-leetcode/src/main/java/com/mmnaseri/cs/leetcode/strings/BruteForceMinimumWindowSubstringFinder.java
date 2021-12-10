package com.mmnaseri.cs.leetcode.strings;

import java.util.HashMap;
import java.util.Map;

public class BruteForceMinimumWindowSubstringFinder implements MinimumWindowSubstringFinder {

  @Override
  public String minWindow(String haystack, String needle) {
    Map<Character, Integer> needleCharmap = charmap(needle);
    String min = "";
    for (int i = 0; i < haystack.length(); i++) {
      for (int j = i; j < haystack.length(); j++) {
        String substring = haystack.substring(i, j + 1);
        if ((min.isEmpty() || min.length() > substring.length())
            && contains(needleCharmap, charmap(substring))) {
          min = substring;
        }
      }
    }
    return min;
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
