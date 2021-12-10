package com.mmnaseri.cs.leetcode.strings;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.HashMap;
import java.util.Map;

@Quality(Stage.DOCUMENTED)
public class SlidingWindowLongestSubstringWithDistinctChars
    implements LongestSubstringWithDistinctChars {

  @Complexity(
      value = "O(n)",
      explanation = "we overreach and correct, but each character is seen and forgotten once.")
  @Override
  public int find(String str, int distinctChars) {
    int max = -1;
    int start = 0;
    Map<Character, Integer> windowChars = new HashMap<>();
    for (int i = 0; i < str.length(); i++) {
      windowChars.put(str.charAt(i), windowChars.getOrDefault(str.charAt(i), 0) + 1);
      while (windowChars.size() > distinctChars) {
        windowChars.put(str.charAt(start), windowChars.get(str.charAt(start)) - 1);
        if (windowChars.get(str.charAt(start)) == 0) {
          windowChars.remove(str.charAt(start));
        }
        start++;
      }
      if (windowChars.size() == distinctChars) {
        max = Math.max(max, i - start + 1);
      }
    }
    return max;
  }
}
