package com.mmnaseri.cs.clrs.ch07.s1;

import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;

import static com.mmnaseri.cs.clrs.ch07.QuickSortUtils.quickSortPartition;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (6/6/15, 4:22 PM)
 */
@Quality(Stage.TESTED)
public class QuickSorter<E extends Comparable<E>> implements Sorter<E> {

  private final Comparator<E> comparator;

  public QuickSorter(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  private void sort(E[] items, int from, int to) {
    if (from < to - 1) {
      if (to - from == 2) {
        if (comparator.compare(items[from], items[to - 1]) > 0) {
          ArrayUtils.swap(items, from, to - 1);
        }
        return;
      }
      int middle = partition(items, from, to);
      sort(items, from, middle);
      sort(items, middle + 1, to);
    }
  }

  protected int partition(E[] items, int from, int to) {
    return quickSortPartition(comparator, items, from, to);
  }

  @Override
  public void sort(E[] items) {
    sort(items, 0, items.length);
  }
}
