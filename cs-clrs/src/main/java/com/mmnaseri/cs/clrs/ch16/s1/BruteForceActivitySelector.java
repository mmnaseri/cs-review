package com.mmnaseri.cs.clrs.ch16.s1;

import com.mmnaseri.cs.clrs.ch15.s4.SequenceUtils;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15)
 */
@Quality(
    value = Stage.DOCUMENTED,
    explanation = "poorest design and running time possible (O(2^n * n^2))")
public class BruteForceActivitySelector implements ActivitySelector {

  @Override
  @Complexity(
      value = "O(n^2 * 2^n)",
      explanation = "`findProperCandidate` is the most significant operation")
  public Set<Integer> select(Activity... activities) {
    // we first note the index of the activities so that after sorting we can recall where each item
    // was
    final IndexedActivity[] indexedActivities = IndexedActivity.index(activities);
    // now we calculate all the possible subsequences of the given list
    final List<List<IndexedActivity>> sequences = generateSequences(indexedActivities);
    // next we sort these sub-sequences so that the most profitable candidate is evaluated first
    sortAllSubSequences(sequences);
    // we now sort each sub-sequence so that items appear with their natural order
    sortIndividualSubSequence(sequences);
    // now we progressively process each subset to find the first one that matches our specification
    return findProperCandidate(sequences);
  }

  @Complexity(
      value = "O(n^2 * 2^n)",
      explanation =
          "There are 2^n subsequences and each one is validated using O(n^2) compatibility control")
  private Set<Integer> findProperCandidate(List<List<IndexedActivity>> sequences) {
    final Set<Integer> indices = new HashSet<>();
    for (List<IndexedActivity> sequence : sequences) {
      if (isCompatible(sequence)) {
        for (IndexedActivity indexedActivity : sequence) {
          indices.add(indexedActivity.getIndex());
        }
        return indices;
      }
    }
    return indices;
  }

  @Complexity(
      value = "O(2^n * nlgn)",
      explanation =
          "Each subsequence is of size order O(n) and merge sorting it requires O(nlgn) and there are 2^n such lists")
  private void sortIndividualSubSequence(List<List<IndexedActivity>> sequences) {
    for (List<IndexedActivity> sequence : sequences) {
      Collections.sort(sequence);
    }
  }

  @Complexity(
      value = "O(n2^n)",
      explanation =
          "We are sorting a collection of size 2^n using merge sort which is O(2^nlg(2^n)) = O(n2^n)")
  private void sortAllSubSequences(List<List<IndexedActivity>> sequences) {
    sequences.sort(
        (first, second) -> {
          final int comparison = Integer.compare(second.size(), first.size());
          if (comparison == 0) {
            // our algorithm should go for the sequence that finishes sooner (which leaves the
            // most amount of unused space)
            return Integer.compare(
                first.get(first.size() - 1).getFinish(), second.get(second.size() - 1).getFinish());
          }
          return comparison;
        });
  }

  @Complexity(
      value = "O(2^n)",
      explanation =
          "There are 2^n possible subsets for each set of size n, and we are generating them all dynamically")
  private List<List<IndexedActivity>> generateSequences(IndexedActivity[] indexedActivities) {
    return SequenceUtils.subSequences(Arrays.asList(indexedActivities));
  }

  @Complexity(
      value = "O(n^2)",
      explanation = "Each two activities are compared once: O(selection 2 of n) = O(n^2)")
  private static boolean isCompatible(List<? extends Activity> activities) {
    for (int i = 0; i < activities.size(); i++) {
      for (int j = 0; j < i; j++) {
        if (!activities.get(i).isCompatible(activities.get(j))) {
          return false;
        }
      }
    }
    return true;
  }
}
