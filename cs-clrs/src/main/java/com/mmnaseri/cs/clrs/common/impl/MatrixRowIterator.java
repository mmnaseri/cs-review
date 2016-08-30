package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixRow;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:51 PM)
 */
public class MatrixRowIterator<E> implements Iterator<MatrixRow<E>> {

    private final Matrix<E> matrix;
    private int row;

    public MatrixRowIterator(Matrix<E> matrix) {
        this.matrix = matrix;
        this.row = 0;
    }

    @Override
    public boolean hasNext() {
        return row < matrix.getRows();
    }

    @Override
    public MatrixRow<E> next() {
        return new ImmutableMatrixRow<>(matrix, row ++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
