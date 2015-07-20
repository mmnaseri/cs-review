package com.mmnaseri.cs.clrs.ch15.s3;

import com.mmnaseri.cs.clrs.ch15.s2.MatrixChainParenthesizer;
import com.mmnaseri.cs.clrs.ch15.s2.MatrixParenthesization;
import com.mmnaseri.cs.clrs.ch15.s2.SplitSpecification;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
public class RecursiveMatrixChainParenthesizer implements MatrixChainParenthesizer {

    @Override
    public MatrixParenthesization parenthesize(int... dimensions) {
        final int n = dimensions.length - 1;
        final MatrixParenthesization parenthesization = new MatrixParenthesization();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                parenthesization.note(i, j, new SplitSpecification(Integer.MAX_VALUE, -1));
            }
        }
        return lookup(parenthesization, dimensions, 1, n);
    }

    private MatrixParenthesization lookup(MatrixParenthesization parenthesization, int[] dimensions, int i, int j) {
        if (parenthesization.get(i, j).getOperations() < Integer.MAX_VALUE) {
            return parenthesization;
        }
        if (i == j) {
            parenthesization.note(i, j, new SplitSpecification(0, -1));
        } else {
            for (int k = i; k < j; k ++) {
                final int leftHalf = lookup(parenthesization, dimensions, i, k).get(i, k).getOperations();
                final int rightHalf = lookup(parenthesization, dimensions, k + 1, j).get(k + 1, j).getOperations();
                final int current = dimensions[i - 1] * dimensions[k] * dimensions[j];
                final int value = leftHalf + rightHalf + current;
                if (value < parenthesization.get(i, j).getOperations()) {
                    parenthesization.note(i, j, new SplitSpecification(value, k));
                }
            }
        }
        return parenthesization;
    }

}
