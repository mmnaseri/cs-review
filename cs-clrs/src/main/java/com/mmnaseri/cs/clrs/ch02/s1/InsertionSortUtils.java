package com.mmnaseri.cs.clrs.ch02.s1;

import java.util.Comparator;
import java.util.function.Supplier;

/** Utilities for the insertion sort implementations. */
public class InsertionSortUtils {

  /** Finds the placement of the given item in a prefix of the original array. */
  public static <E> int findPlacementUsingBinarySearch(
      E[] items, E item, int length, Comparator<E> comparator) {
    int from = 0;
    int to = length;
    while (to > from) {
      int cursor = (from + to) / 2;
      if (comparator.compare(item, items[cursor]) < 0) {
        to = cursor;
      } else {
        from = cursor + 1;
      }
    }
    return from;
  }

  public static <E> int placementSpecialCaseHandler(
      E[] items,
      E item,
      int length,
      Comparator<E> comparator,
      Supplier<Integer> regularCaseHAndler) {
    if (comparator.compare(items[0], item) > 0) {
      return 0;
    } else if (comparator.compare(items[length - 1], item) < 0) {
      return length;
    }
    return regularCaseHAndler.get();
  }
}
