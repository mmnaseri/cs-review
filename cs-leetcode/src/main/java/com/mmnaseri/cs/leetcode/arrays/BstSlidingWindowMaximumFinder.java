package com.mmnaseri.cs.leetcode.arrays;

import com.mmnaseri.cs.clrs.ch12.s3.SimpleBinarySearchTree;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

@Quality(Stage.DOCUMENTED)
public class BstSlidingWindowMaximumFinder implements SlidingWindowMaximumFinder {

  @Complexity(
      value = "O(n . log(k))",
      explanation =
          "where n is the size of data and k is the window size, since we use a BST for this")
  @Override
  public int[] findMaximums(int[] data, int windowSize) {
    SimpleBinarySearchTree<Integer> tree =
        new SimpleBinarySearchTree<>(Comparator.<Integer>naturalOrder());
    int[] maximums = new int[data.length - windowSize + 1];
    for (int i = 0; i < data.length + 1; i++) {
      if (i >= windowSize) {
        maximums[i - windowSize] = tree.maximum().getValue();
        tree.delete(data[i - windowSize]);
      }
      if (i < data.length) {
        tree.insert(data[i]);
      }
    }
    return maximums;
  }
}
