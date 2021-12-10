package com.mmnaseri.cs.clrs.ch07;

import com.mmnaseri.cs.clrs.common.ArrayUtils;

import java.util.Comparator;

public final class QuickSortUtils {

  private QuickSortUtils() throws IllegalAccessException {
    throw new IllegalAccessException("This type shouldn't be instantiated.");
  }

  public static <E extends Comparable<E>> int quickSortPartition(
    Comparator<E> comparator, E[] items, int from, int to) {
    final E partition = items[to - 1];
    int smaller = from - 1;
    int seen = from;
    while (seen < to - 1) {
      if (comparator.compare(partition, items[seen]) >= 0) {
        smaller++;
        ArrayUtils.swap(items, smaller, seen);
      }
      seen++;
    }
    ArrayUtils.swap(items, smaller + 1, to - 1);
    return smaller + 1;
  }


}
