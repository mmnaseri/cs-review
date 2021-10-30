package com.mmnaseri.cs.clrs.ch02.s1;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/27/15)
 */
@Quality(Stage.TESTED)
public class FasterRecursiveInsertionSorter<E extends Comparable<E>>
    extends RecursiveInsertionSorter<E> {

  public FasterRecursiveInsertionSorter(Comparator<E> comparator) {
    super(comparator);
  }

  /**
   * This method tries to cut the running time by a constant factor or offset by treating the edge
   * cases of when the item is smaller than all items and when the item is larger than all items as
   * special cases.
   *
   * @param items all the items
   * @param item the item which we want to place
   * @param length the length of the array which should be visited
   * @param comparator the comparator
   * @return the expected position of the item in the given array
   */
  @Override
  protected int findPlacement(E[] items, E item, int length, Comparator<E> comparator) {
    return InsertionSortUtils.placementSpecialCaseHandler(
        items,
        item,
        length,
        comparator,
        () -> super.findPlacement(items, item, length, comparator));
  }
}
