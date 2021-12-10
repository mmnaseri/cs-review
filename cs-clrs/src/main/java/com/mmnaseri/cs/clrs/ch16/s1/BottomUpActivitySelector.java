package com.mmnaseri.cs.clrs.ch16.s1;

import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/23/15)
 */
@Quality(Stage.DOCUMENTED)
public class BottomUpActivitySelector implements ActivitySelector {

  @Complexity("O(n^3)")
  @Override
  public Set<Integer> select(Activity... activities) {
    // we need to remember the original position of the activities, so that when we are reporting
    // them back
    // the sorting we are about to do does not affect the external environment
    final IndexedActivity[] indexedActivities = prepareIndexedActivities(activities);
    // this matrix will hold the count of the longest selection of activities starting with a[i] and
    // ending with a[j]
    // in selection[i][j]. Since we only need the upper half for this, we use the lower half in
    // symmetric form to
    // store the location of one of the activities between a[i] and a[j], a[k], such that a[k] is
    // compatible with
    // both a[i] and a[j]. If no such a[k] exists, we put `-1` in place of the index
    final int[][] selection = prepareSelectionMatrix(indexedActivities);
    // this is for pointing out the latest discovered longest activity planning sequence
    Range best = findRange(indexedActivities, selection);
    // we now have to construct the list of indices
    final Set<Integer> result = new HashSet<>();
    final Set<Integer> indexes = collectIndexes(selection, best.getStart(), best.getEnd());
    for (Integer index : indexes) {
      result.add(indexedActivities[index].getIndex());
    }
    return result;
  }

  @Complexity(
      value = "O(n^3)",
      explanation = "We are ranging three cursors over n in three nested loops")
  private Range findRange(IndexedActivity[] indexedActivities, int[][] selection) {
    Range best = new Range(0, 0);
    // the length of activity schedules
    for (int length = 1; length <= indexedActivities.length; length++) {
      // beginning the schedule
      for (int i = 0; i < indexedActivities.length - length; i++) {
        // end of the schedule
        final int j = i + length;
        // if the beginning and the end are not compatible, we can't pick them
        if (indexedActivities[i].getFinish() > indexedActivities[j].getStart()) {
          continue;
        }
        // at least the beginning and the end are compatible
        selection[i][j] = 2;
        // we select something in between
        for (int k = i + 1; k < j; k++) {
          // the selected mid-point has to be compatible with both ends
          if (indexedActivities[i].getFinish() > indexedActivities[k].getStart()
              || indexedActivities[k].getFinish() > indexedActivities[j].getStart()) {
            continue;
          }
          // the number of activities between `i` and `k`
          final int left = selection[i][k];
          // the number of activities between `k` and `j`
          final int right = selection[k][j];
          // we subtract one because we have counted `k` twice (once in left and once in right)
          final int value = left + right - 1;
          if (value >= selection[i][j]) {
            // we note the length of the sequence
            selection[i][j] = value;
            // we note the index of the midpoint
            selection[j][i] = k;
            // if this sequence is better than what was found before, we mark this as the best
            if (selection[best.getStart()][best.getEnd()] <= value) {
              best = new Range(i, j);
            }
          }
        }
      }
    }
    return best;
  }

  @Complexity(value = "O(n^2)", explanation = "We are walking the hole matrix")
  private int[][] prepareSelectionMatrix(IndexedActivity[] indexedActivities) {
    final int[][] selection = new int[indexedActivities.length][];
    for (int i = 0; i < indexedActivities.length; i++) {
      selection[i] = new int[indexedActivities.length];
      for (int j = 0; j < indexedActivities.length; j++) {
        if (i > j) {
          // bottom half is for indices
          selection[i][j] = -1;
        } else {
          // upper half is for lengths
          selection[i][j] = 0;
        }
      }
    }
    return selection;
  }

  @Complexity(value = "O(n * lg(n))", explanation = "We are using mergeSort")
  private IndexedActivity[] prepareIndexedActivities(Activity[] activities) {
    final IndexedActivity[] indexedActivities = IndexedActivity.index(activities);
    Arrays.sort(indexedActivities);
    return indexedActivities;
  }

  @Complexity(
      value = "O(n)",
      explanation =
          "Because we are at most visiting each range once, and no two ranges overlap except at the beginning and the end")
  private Set<Integer> collectIndexes(int[][] selection, int start, int end) {
    final Set<Integer> result = new HashSet<>();
    // we add the start and the end (potentially the same)
    result.add(start);
    result.add(end);
    if (end - start > 1) {
      // we go to the midpoint
      final int middle = selection[end][start];
      if (middle != -1) {
        // if midpoint is not `-1` then activity a[middle] exists such that a[middle] is compatible
        // with
        // both a[start] and a[end]. So, we need to divide the list a[start] ... a[end] into two
        // parts using a[middle] as partition
        final Set<Integer> left = collectIndexes(selection, start, middle);
        final Set<Integer> right = collectIndexes(selection, middle, end);
        result.addAll(left);
        result.addAll(right);
      }
    }
    return result;
  }

  private static class Range {

    private final int start;
    private final int end;

    private Range(int start, int end) {
      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public int getEnd() {
      return end;
    }
  }
}
