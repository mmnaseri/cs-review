package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixRow;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 4:11 PM)
 */
public abstract class AbstractMatrix<E> implements Matrix<E> {

    private static final String EMPTY_MATRIX = "(empty matrix)";

    @Override
    public E get(int row, int col) {
        checkIndices(row, col);
        return read(row, col);
    }

    protected abstract E read(int row, int col);

    @Override
    public void set(int row, int col, E value) {
        checkIndices(row, col);
        write(row, col, value);
    }

    protected abstract void write(int row, int col, E value);

    protected void checkIndices(int row, int col) {
        if (row < 0 || row >= getRows()) {
            throw new IndexOutOfBoundsException("Row index out of bounds(0," + (getRows() - 1) + "): " + row);
        }
        if (col < 0 || col >= getColumns()) {
            throw new IndexOutOfBoundsException("Columns index out of bounds(0," + (getColumns() - 1) + "): " + col);
        }
    }

    @Override
    public Iterator<MatrixRow<E>> iterator() {
        return new MatrixRowIterator<>(this);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (MatrixRow<E> row : this) {
            builder.append(row).append("\n");
        }
        if (builder.length() == 0) {
            return EMPTY_MATRIX;
        }
        return builder.substring(0, builder.length() - 1);
    }

}
