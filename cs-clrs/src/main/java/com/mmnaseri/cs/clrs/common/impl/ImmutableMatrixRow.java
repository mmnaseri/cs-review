package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:52 PM)
 */
public class ImmutableMatrixRow<E> implements MatrixRow<E> {

    private final Matrix<E> matrix;
    private final int row;

    public ImmutableMatrixRow(Matrix<E> matrix, int row) {
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public int getRowNumber() {
        return row;
    }

    @Override
    public Iterator<MatrixCell<E>> iterator() {
        return new MatrixCellIterator<>(matrix, row);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[");
        boolean first = true;
        for (int i = 0; i < matrix.getColumns(); i++) {
            if (!first) {
                builder.append(",");
            } else {
                first = false;
            }
            builder.append(String.valueOf(matrix.get(row, i)));
        }
        builder.append("]");
        return builder.toString();
    }

}
