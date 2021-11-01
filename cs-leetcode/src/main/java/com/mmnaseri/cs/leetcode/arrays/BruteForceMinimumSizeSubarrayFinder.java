package com.mmnaseri.cs.leetcode.arrays;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

@Quality(Stage.DOCUMENTED)
public class BruteForceMinimumSizeSubarrayFinder implements MinimumSizeSubarrayFinder {

  @Complexity("O(n^2)")
  @Override
  public int minSubArrayLen(int target, int[] nums) {
    Integer minLength = null;
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if ((minLength == null || minLength > j - i) && sum >= target) {
          minLength = j - i + 1;
        }
      }
    }
    return minLength == null ? 0 : minLength;
  }
}

