package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:54 PM)
 */
public class MatrixCellIterator<E> implements Iterator<MatrixCell<E>> {

    private final Matrix<E> matrix;
    private final int row;
    private int column;

    public MatrixCellIterator(Matrix<E> matrix, int row) {
        this.matrix = matrix;
        this.row = row;
        column = 0;
    }

    @Override
    public boolean hasNext() {
        return column < matrix.getColumns();
    }

    @Override
    public MatrixCell<E> next() {
        return new ImmutableMatrixCell<>(row, column ++, matrix);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
