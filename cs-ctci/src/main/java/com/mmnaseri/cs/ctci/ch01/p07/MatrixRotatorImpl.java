package com.mmnaseri.cs.ctci.ch01.p07;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 12:11 PM)
 */
public class MatrixRotatorImpl implements MatrixRotator {

    @Override
    public <E> void rotate(E[][] matrix) {
        for (int offset = 0; offset < matrix.length; offset ++) {
            for (int i = 0; i < matrix.length - offset * 2 - 1; i++) {
                final E top = matrix[offset][offset + i];
                final E bottom = matrix[matrix.length - offset - 1][matrix.length - offset - 1 - i];
                final E left = matrix[matrix.length - offset - i - 1][offset];
                final E right = matrix[offset + i][matrix.length - offset - 1];
                matrix[offset + i][matrix.length - offset - 1] = top;
                matrix[matrix.length - offset - 1][matrix.length - offset - 1 - i] = right;
                matrix[matrix.length - offset - i - 1][offset] = bottom;
                matrix[offset][offset + i] = left;
            }
        }
    }

}
