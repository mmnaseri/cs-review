package com.mmnaseri.cs.leetcode.arrays;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSubSequenceFinder {

  public int longestConsecutive(int[] nums) {
    Set<Integer> hash = new HashSet<>();
    for (int num : nums) {
      hash.add(num);
    }
    int longest = 0;
    for (int i = 0; i < nums.length; i ++) {
      if (!hash.contains(nums[i] - 1)) {
        int j = i;
        while (hash.contains(nums[i] + j - i)) {
          j++;
        }
        longest = Math.max(j - i, longest);
      }
    }
    return longest;
  }

}
