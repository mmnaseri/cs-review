package com.mmnaseri.cs.leetcode.arrays;

/**
 * <a
 * href="https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/834/">Problem
 * here.</a>
 */
public class DuplicateNumberFinder {
  public int findDuplicate(int[] nums) {
    int low = 1;
    int high = nums.length - 1;
    int duplicate = -1;

    while (low <= high) {
      int midpoint = (low + high) / 2;
      int left = 0;

      for (int num : nums) {
        if (num <= midpoint) {
          left++;
        }
      }

      if (left > midpoint) {
        duplicate = midpoint;
        high = midpoint - 1;
      } else {
        low = midpoint + 1;
      }
    }
    return duplicate;
  }
}
