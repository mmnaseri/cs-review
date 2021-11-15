package com.mmnaseri.cs.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemoizedLargestDivisibleSubsetFinder implements LargestDivisibleSubsetFinder {

  @Override
  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);

    int[] divisors = new int[nums.length];
    Arrays.fill(divisors, 1);

    int[] previous = new int[nums.length];
    Arrays.fill(previous, -1);

    int max = 0;

    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0 && divisors[i] < divisors[j] + 1) {
          previous[i] = j;
          divisors[i] = divisors[j] + 1;
        }
      }
      if (divisors[i] > divisors[max]) {
        max = i;
      }
    }
    List<Integer> integers = new ArrayList<>();
    int index = max;
    while (index >= 0) {
      integers.add(nums[index]);
      index = previous[index];
    }
    return integers;
  }
}
