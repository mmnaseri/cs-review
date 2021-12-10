package com.mmnaseri.cs.leetcode.arrays;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

@Quality(Stage.DOCUMENTED)
public class BruteForceSlidingWindowMaximumFinder implements SlidingWindowMaximumFinder {

  @Complexity(
      value = "O(n . k)",
      explanation = "where n is the size of the input, and k is the window size")
  @Override
  public int[] findMaximums(int[] data, int windowSize) {
    int[] maximums = new int[data.length - windowSize + 1];
    for (int i = 0; i < data.length - windowSize + 1; i++) {
      maximums[i] = data[i];
      for (int j = i; j < i + windowSize; j++) {
        maximums[i] = Math.max(maximums[i], data[j]);
      }
    }
    return maximums;
  }
}
