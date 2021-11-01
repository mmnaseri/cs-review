package com.mmnaseri.cs.leetcode.arrays;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

@Quality(Stage.BUGGY)
public class FirstMissingPositive {

  public int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0
          && i != nums[i] - 1
          && nums[i] - 1 < nums.length
          && nums[nums[i] - 1] != nums[i]) {
        int temp = nums[nums[i] - 1];
        nums[nums[i] - 1] = nums[i];
        nums[i] = temp;
      } else {
        i++;
      }
    }
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i + 1] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }
}
