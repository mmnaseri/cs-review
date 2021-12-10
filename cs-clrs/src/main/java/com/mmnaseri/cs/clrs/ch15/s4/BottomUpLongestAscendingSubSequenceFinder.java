package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15)
 */
@Quality(Stage.TESTED)
public class BottomUpLongestAscendingSubSequenceFinder<E>
    extends AbstractLongestAscendingSubSequenceFinder<E> {

  public BottomUpLongestAscendingSubSequenceFinder(Comparator<E> comparator) {
    super(comparator);
  }

  @Override
  public List<E> find(List<E> items) {
    final int[] length = new int[items.size() + 1];
    final int[] pointer = new int[items.size() + 1];
    int tail = 0;
    int longest = 0;
    length[0] = 0;
    pointer[0] = -1;
    for (int i = 1; i < items.size() + 1; i++) {
      int max = Integer.MIN_VALUE;
      pointer[i] = 0;
      for (int j = i - 1; j >= 0; j--) {
        if ((j == 0 || lessThan(items.get(j - 1), items.get(i - 1))) && length[j] > max) {
          max = length[j];
          pointer[i] = j;
        }
      }
      length[i] = length[pointer[i]] + 1;
      if (length[i] > longest) {
        longest = length[i];
        tail = i;
      }
    }
    final List<E> list = new ArrayList<>();
    while (tail > 0) {
      list.add(0, items.get(tail - 1));
      tail = pointer[tail];
    }
    return list;
  }
}
