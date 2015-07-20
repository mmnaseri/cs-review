package com.mmnaseri.cs.clrs.ch15.s2;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15, 5:40 AM)
 */
public class BottomUpMatrixChainParenthesizer implements MatrixChainParenthesizer {

    @Override
    public MatrixParenthesization parenthesize(int... dimensions) {
        final MatrixParenthesization parenthesization = new MatrixParenthesization();
        for (int i = 0; i < dimensions.length; i ++) {
            parenthesization.note(i, i, new SplitSpecification(0, -1));
        }
        for (int length = 1; length < dimensions.length; length ++) {
            for (int i = 0; i < dimensions.length - length; i ++) {
                final int j = i + length;
                parenthesization.note(i, j, new SplitSpecification(Integer.MAX_VALUE, -1));
                for (int k = i; k < j; k ++) {
                    final int current = parenthesization.get(i, k).getOperations() + parenthesization.get(k + 1, j).getOperations() + dimensions[i - 1] * dimensions[k] * dimensions[j];
                    if (current < parenthesization.get(i, j).getOperations()) {
                        parenthesization.note(i, j, new SplitSpecification(current, k));
                    }
                }
            }
        }
        return parenthesization;
    }

}
