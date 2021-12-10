package com.mmnaseri.cs.clrs.ch15.s5;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15, 9:28 PM)
 */
@Quality(Stage.UNTESTED)
public class BottomUpOptimalBinarySearchTreeSpecifier implements OptimalBinarySearchTreeSpecifier {

  @Override
  public BinarySearchTreeSpecification find(double[] hits, double[] misses) {
    if (hits == null || misses == null) {
      throw new NullPointerException();
    }
    if (hits.length != misses.length - 1) {
      throw new IllegalArgumentException();
    }
    final BinarySearchTreeSpecification specification = new BinarySearchTreeSpecification();
    for (int i = 1; i <= misses.length; i++) {
      specification.set(i, i - 1, new SplitSpecification(-1, misses[i - 1], misses[i - 1]));
    }
    for (int length = 1; length <= hits.length; length++) {
      for (int i = 1; i <= misses.length - length; i++) {
        final int j = i + length - 1;
        specification.set(
            i,
            j,
            new SplitSpecification(
                -1,
                Integer.MAX_VALUE,
                specification.get(i, j - 1).getWeight() + hits[j - 1] + misses[j]));
        for (int root = i; root <= j; root++) {
          final double left = specification.get(i, root - 1).getExpectation();
          final double right = specification.get(root + 1, j).getExpectation();
          final double current = specification.get(i, j).getWeight();
          final double expectation = left + right + current;
          if (expectation < specification.get(i, j).getExpectation()) {
            specification.set(
                i,
                j,
                new SplitSpecification(root - 1, expectation, specification.get(i, j).getWeight()));
          }
        }
      }
    }
    return specification;
  }
}
