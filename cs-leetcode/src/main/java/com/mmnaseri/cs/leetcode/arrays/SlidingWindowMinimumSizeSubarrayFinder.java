package com.mmnaseri.cs.leetcode.arrays;

public class SlidingWindowMinimumSizeSubarrayFinder implements MinimumSizeSubarrayFinder {

  @Override
  public int minSubArrayLen(int target, int[] nums) {
    int sum = 0;
    Integer min = null;
    int start = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (sum >= target) {
        min = Math.min(min == null ? Integer.MAX_VALUE : min, i - start + 1);
        while (sum >= target && start <= i) {
          sum -= nums[start];
          start++;
          if (sum >= target) {
            min = Math.min(min, i - start + 1);
          }
        }
      }
    }
    return min == null ? 0 : min;
  }

}
