package com.mmnaseri.cs.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BruteForceLargestDivisibleSubsetFinder implements LargestDivisibleSubsetFinder {

  @Override
  public List<Integer> largestDivisibleSubset(int[] nums) {
    return largestDivisibleSubset(Arrays.stream(nums).boxed().collect(Collectors.toList()));
  }

  private List<Integer> largestDivisibleSubset(List<Integer> nums) {
    if (nums.size() <= 1) {
      return nums;
    }
    if (isValid(nums)) {
      return nums;
    }
    List<Integer> max = List.of();
    for (int i = 0; i < nums.size(); i++) {
      List<Integer> list = new ArrayList<>(nums);
      // This is in fact not suspicious at all. We are removing the ith element so that we
      // generate the next permutation.
      //noinspection SuspiciousListRemoveInLoop
      list.remove(i);
      List<Integer> agenda = largestDivisibleSubset(list);
      if (agenda.size() > max.size()) {
        max = agenda;
      }
    }
    return max;
  }

  private static boolean isValid(List<Integer> list) {
    for (int i = 0; i < list.size(); i++) {
      Integer first = list.get(i);
      for (int j = 0; j < list.size(); j++) {
        if (i == j) {
          continue;
        }
        Integer second = list.get(j);
        if (!(first % second == 0) && !(second % first == 0)) {
          return false;
        }
      }
    }
    return true;
  }
}
