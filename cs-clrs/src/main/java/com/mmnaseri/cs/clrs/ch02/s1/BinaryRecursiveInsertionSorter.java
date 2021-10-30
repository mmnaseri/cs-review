package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/27/15)
 */
@Quality(Stage.TESTED)
public class BinaryRecursiveInsertionSorter<E extends Comparable<E>>
    extends RecursiveInsertionSorter<E> {

  public BinaryRecursiveInsertionSorter(Comparator<E> comparator) {
    super(comparator);
  }

  /**
   * This method uses the binary search method for finding the place where the indicated item should
   * be placed.
   *
   * @param items all the items
   * @param item the item which we want to place
   * @param length the length of the array which should be visited
   * @param comparator the comparator
   * @return the expected position of the item in the given array
   */
  @Override
  @Complexity(
      value = "O(ln(n))",
      explanation = "since we halve the search target until the width of the search area is 1")
  protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
    return InsertionSortUtils.findPlacementUsingBinarySearch(items, item, length, comparator);
  }
}
