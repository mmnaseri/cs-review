package com.mmnaseri.cs.leetcode.arrays;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

@Quality(Stage.DOCUMENTED)
public class DequeSlidingWindowMaximumFinder implements SlidingWindowMaximumFinder {

  @Complexity(
      value = "O(n)",
      explanation =
          "where n is the data.length; observe that each element is at most added to the deque once and removed once.")
  @Override
  public int[] findMaximums(int[] data, int windowSize) {
    int[] maximums = new int[data.length - windowSize + 1];
    // We have a choice of LinkedList and ArrayDeque; LinkedList is more performant.
    Deque<Integer> deque = new LinkedList<>();
    // Since we add the values to the deque at the very end of each iteration, we need to run this
    // loop an extra iteration.
    for (int i = 0; i <= data.length; i++) {
      if (i >= windowSize) {
        // The current front of the deck is our max.
        Integer max = Objects.requireNonNull(deque.peek(), "Maximum value should not be null");
        maximums[i - windowSize] = data[max];
        // If the front of the deque has fallen off of the window, we are no longer interested in
        // it.
        while (!deque.isEmpty() && deque.peek() <= (i - windowSize)) {
          deque.removeFirst();
        }
      }
      // Since we are running the loop one extra step, we need to make sure we don't cause
      // overreaches by checking against the length.
      if (i < data.length) {
        // Remove all values from the back of the deque that are smaller than the item we are just
        // about to add.
        while (!deque.isEmpty() && data[deque.peekLast()] <= data[i]) {
          deque.removeLast();
        }
        deque.add(i);
      }
    }
    return maximums;
  }
}
