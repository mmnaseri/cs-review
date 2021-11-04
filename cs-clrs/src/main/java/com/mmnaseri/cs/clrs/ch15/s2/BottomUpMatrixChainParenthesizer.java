package com.mmnaseri.cs.clrs.ch15.s2;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15, 5:40 AM)
 */
@Quality(value = Stage.UNTESTED)
public class BottomUpMatrixChainParenthesizer implements MatrixChainParenthesizer {

  @Override
  public MatrixParenthesization parenthesize(int... dimensions) {
    final MatrixParenthesization parenthesization = new MatrixParenthesization();
    final int matrices = dimensions.length - 1;
    for (int i = 1; i <= matrices; i++) {
      // the base case
      parenthesization.note(i, i, new SplitSpecification());
    }
    // we loop the length over all the useful chain lengths (only two or all items or anything in
    // between)
    for (int length = 2; length <= matrices; length++) {
      for (int i = 1; i <= matrices - length + 1; i++) {
        final int j = i + length - 1;
        parenthesization.note(i, j, new SplitSpecification(Integer.MAX_VALUE, -1));
        for (int k = i; k <= j - 1; k++) {
          final int rightHalf = parenthesization.get(i, k).getOperations();
          final int leftHalf = parenthesization.get(k + 1, j).getOperations();
          final int current = dimensions[i - 1] * dimensions[k] * dimensions[j];
          final int operations = rightHalf + leftHalf + current;
          if (parenthesization.get(i, j).getOperations() > operations) {
            parenthesization.note(i, j, new SplitSpecification(operations, k));
          }
        }
      }
    }
    return parenthesization;
  }
}
