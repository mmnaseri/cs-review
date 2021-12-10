package com.mmnaseri.cs.leetcode.strings;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

@Quality(Stage.DOCUMENTED)
public class BruteForceLongestSubstringWithDistinctChars
    implements LongestSubstringWithDistinctChars {

  @Complexity("O(n^2)")
  @Override
  public int find(String str, int distinctChars) {
    int max = -1;
    for (int i = 0; i < str.length(); i++) {
      for (int j = i; j < str.length(); j++) {
        int distinctCharsInWindow = (int) str.substring(i, j).chars().distinct().count();
        int windowSize = j - i + 1;
        if (distinctCharsInWindow == distinctChars && windowSize > max) {
          max = windowSize;
        }
      }
    }
    return max;
  }
}
