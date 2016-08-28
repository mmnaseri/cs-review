package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:56 PM)
 */
public class ImmutableMatrixCell<E> implements MatrixCell<E> {

    private final int row;
    private final int column;
    private final Matrix<E> matrix;

    public ImmutableMatrixCell(int row, int column, Matrix<E> matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
    }

    @Override
    public int getRowNumber() {
        return row;
    }

    @Override
    public int getColumnNumber() {
        return column;
    }

    @Override
    public E getValue() {
        return matrix.get(row, column);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

}
