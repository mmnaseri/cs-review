package com.mmnaseri.cs.ctci.ch01.p08;

import sun.security.util.BitArray;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/25/16, 12:35 PM)
 */
public class ZeroMatrixImpl implements ZeroMatrix {

    @Override
    public void setZero(int[][] matrix) {
        final BitArray rows = new BitArray(matrix.length);
        final BitArray cols = new BitArray(matrix[0].length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                final int item = matrix[i][j];
                rows.set(i, rows.get(i) || item == 0);
                cols.set(j, cols.get(j) || item == 0);
            }
        }
        for (int i = 0; i < rows.length(); i++) {
            for (int j = 0; j < cols.length(); j++) {
                if (rows.get(i) || cols.get(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
