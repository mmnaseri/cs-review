package com.mmnaseri.cs.clrs.ch15.s4;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
@Quality(value = Stage.TESTED, explanation = "Dumbest way to do it, but works with O(2^n)")
public class BruteForceLongestAscendingSubSequenceFinder<E>
    extends AbstractLongestAscendingSubSequenceFinder<E> {

  public BruteForceLongestAscendingSubSequenceFinder(Comparator<E> comparator) {
    super(comparator);
  }

  @Override
  public List<E> find(List<E> items) {
    final List<List<E>> sequences = SequenceUtils.subSequences(items);
    sequences.sort(Comparator.comparingInt(List::size));
    for (List<E> sequence : sequences) {
      if (isAscending(sequence)) {
        return sequence;
      }
    }
    return Collections.emptyList();
  }

  private boolean isAscending(List<E> sequence) {
    for (int i = 0; i < sequence.size() - 1; i++) {
      if (!lessThan(sequence.get(i), sequence.get(i + 1))) {
        return false;
      }
    }
    return true;
  }
}
