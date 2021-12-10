package com.mmnaseri.cs.clrs.ch09;

import com.mmnaseri.cs.clrs.ch07.QuickSortUtils;
import com.mmnaseri.cs.clrs.common.ArrayUtils;

import java.util.Comparator;

public final class SelectionUtils {

  private SelectionUtils() throws IllegalAccessException {
    throw new IllegalAccessException("This type shouldn't be instantiated.");
  }

  public static <E extends Comparable<E>> int selectPartition(
      Comparator<E> comparator, E[] items, int from, int to, int pivot) {
    ArrayUtils.swap(items, pivot, to - 1);
    return QuickSortUtils.quickSortPartition(comparator, items, from, to);
  }
}
