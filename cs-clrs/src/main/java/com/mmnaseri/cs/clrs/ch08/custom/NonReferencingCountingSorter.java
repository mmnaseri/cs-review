package com.mmnaseri.cs.clrs.ch08.custom;

import com.mmnaseri.cs.clrs.ch08.s2.CountingSorter;
import com.mmnaseri.cs.clrs.common.Sorter;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This implementation offers better space and time complexity than {@link CountingSorter} as it
 * appears in the CLRS book.
 *
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/11/15, 3:12 PM)
 */
@Quality(Stage.TESTED)
public class NonReferencingCountingSorter implements Sorter<Integer> {

  private final Comparator<Integer> comparator;

  public NonReferencingCountingSorter(Comparator<Integer> comparator) {
    this.comparator = comparator;
  }

  @Override
  public void sort(Integer[] items) {
    // we need to take these extra steps to ensure we support negative numbers
    int offset = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (Integer item : items) {
      if (comparator.compare(item, offset) < 0) {
        offset = item;
      }
      if (comparator.compare(item, max) > 0) {
        max = item;
      }
    }
    final Integer[] counts = new Integer[max - offset + 1];
    // initializing the counts
    Arrays.fill(counts, 0);
    // counting the numbers, after this each item in count corresponds to how many of that item
    // there was
    // in the original array
    for (Integer item : items) {
      counts[item - offset]++;
    }
    // this will point to an element in the counts array.
    // the indices in the counts array are basically the items we saw in the items array,
    // so we can use them as the items to be placed in the resulting array
    int cursor = 0;
    for (int i = 0; i < items.length; i++) {
      while (counts[cursor] == 0) {
        cursor++;
      }
      items[i] = cursor + offset;
      counts[cursor]--;
    }
  }
}
