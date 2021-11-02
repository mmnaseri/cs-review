package com.mmnaseri.cs.clrs.ch07.s3;

import com.mmnaseri.cs.clrs.ch07.s1.QuickSorter;
import com.mmnaseri.cs.clrs.common.ArrayUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Comparator;
import java.util.Random;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (6/6/15, 4:41 PM)
 */
@Quality(Stage.TESTED)
public class RandomizedQuickSorter<E extends Comparable<E>> extends QuickSorter<E> {

  public RandomizedQuickSorter(Comparator<E> comparator) {
    super(comparator);
  }

  @Override
  protected int partition(E[] items, int from, int to) {
    final int index = new Random().nextInt(to - from) + from;
    ArrayUtils.swap(items, index, to - 1);
    return super.partition(items, from, to);
  }
}
