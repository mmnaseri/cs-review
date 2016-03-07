package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;

import java.util.Iterator;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 09:58)
 */
public class DelegatingMatrix<E> implements Matrix<E> {

    private final int rowOffset;
    private final int columnOffset;
    private final int rowCount;
    private final int columnCount;
    private final Matrix<E> delegate;

    public DelegatingMatrix(Matrix<E> delegate, int rowOffset, int columnOffset, int rowCount, int columnCount) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.delegate = delegate;
        if (rowOffset < 0 || rowOffset >= delegate.getRows()) {
            throw new IllegalArgumentException("Row offset " + rowOffset + " is out of bounds: [0," + (delegate.getRows() - 1) + "]");
        }
        if (columnOffset < 0 || columnOffset >= delegate.getColumns()) {
            throw new IllegalArgumentException("Column offset " + columnOffset + " is out of bounds: [0," + (delegate.getColumns() - 1) + "]");
        }
        if (rowCount > delegate.getRows() - rowOffset) {
            throw new IllegalArgumentException(String.format("Delegate matrix can only provide rows [%d-%d] while it was asked to provide [%d-%d]", rowOffset, delegate.getRows(), rowOffset, rowOffset + rowCount));
        }
        if (columnCount > delegate.getColumns() - columnOffset) {
            throw new IllegalArgumentException(String.format("Delegate matrix can only provide columns [%d-%d] while it was asked to provide [%d-%d]", columnOffset, delegate.getColumns(), columnOffset, columnOffset + columnCount));
        }
    }

    @Override
    public int getRows() {
        return rowCount;
    }

    @Override
    public int getColumns() {
        return columnCount;
    }

    @Override
    public E get(int row, int col) {
        final int rowIndex = row + rowOffset;
        final int colIndex = columnOffset + col;
        checkIndices(rowIndex, colIndex);
        return delegate.get(rowIndex, colIndex);
    }

    @Override
    public void set(int row, int col, E value) {
        final int rowIndex = row + rowOffset;
        final int colIndex = columnOffset + col;
        checkIndices(rowIndex, colIndex);
        delegate.set(rowIndex, colIndex, value);
    }

    protected void checkIndices(int rowIndex, int colIndex) {
        if (rowIndex - rowOffset < 0 || rowIndex - rowOffset >= getRows()) {
            throw new IndexOutOfBoundsException("Row index out of bounds(0," + (getRows() - 1) + "): " + (rowIndex - rowOffset));
        }
        if (colIndex - columnOffset < 0 || colIndex - columnOffset >= getColumns()) {
            throw new IndexOutOfBoundsException("Column index out of bounds(0," + (getColumns() - 1) + "): " + (colIndex - columnOffset));
        }
    }

    @Override
    public Iterator<MatrixRow<E>> iterator() {
        return new DelegateMatrixRowIterator<>(delegate, rowOffset, columnOffset, getRows(), getColumns());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (MatrixRow<E> row : this) {
            builder.append(row).append("\n");
        }
        return builder.toString().trim();
    }

    private static class DelegateMatrixRowIterator<E> implements Iterator<MatrixRow<E>> {

        private final Matrix<E> delegate;
        private final int rowOffset;
        private final int columnOffset;
        private final int rows;
        private final int cols;
        private int cursor;

        private DelegateMatrixRowIterator(Matrix<E> delegate, int cursor, int columnOffset, int rows, int cols) {
            this.delegate = delegate;
            rowOffset = cursor;
            this.columnOffset = columnOffset;
            this.rows = rows;
            this.cols = cols;
            this.cursor = rowOffset;
        }

        @Override
        public boolean hasNext() {
            return cursor - rowOffset < rows;
        }

        @Override
        public MatrixRow<E> next() {
            return new DelegateMatrixRow<>(delegate, columnOffset, rowOffset, cols, cursor ++);
        }

        @Override
        public void remove() {
        }

    }

    private static class DelegateMatrixRow<E> implements MatrixRow<E> {

        private final Matrix<E> delegate;
        private final int columnOffset;
        private final int rowOffset;
        private final int cols;
        private final int row;

        private DelegateMatrixRow(Matrix<E> delegate, int columnOffset, int rowOffset, int cols, int row) {
            this.delegate = delegate;
            this.columnOffset = columnOffset;
            this.rowOffset = rowOffset;
            this.cols = cols;
            this.row = row;
        }

        @Override
        public int getRowNumber() {
            return row - rowOffset;
        }

        @Override
        public Iterator<MatrixCell<E>> iterator() {
            return new DelegateMatrixCellIterator<>(delegate, columnOffset, rowOffset, cols, row);
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean first = true;
            for (MatrixCell<E> cell : this) {
                if (!first) {
                    builder.append(",");
                } else {
                    first = false;
                }
                builder.append(cell);
            }
            builder.append("]");
            return builder.toString();
        }
    }

    private static class DelegateMatrixCellIterator<E> implements Iterator<MatrixCell<E>> {

        private final Matrix<E> delegate;
        private final int columnOffset;
        private final int rowOffset;
        private final int cols;
        private final int row;
        private int cursor;

        private DelegateMatrixCellIterator(Matrix<E> delegate, int columnOffset, int rowOffset, int cols, int row) {
            this.delegate = delegate;
            this.columnOffset = columnOffset;
            this.rowOffset = rowOffset;
            this.cols = cols;
            this.row = row;
            this.cursor = columnOffset;
        }

        @Override
        public boolean hasNext() {
            return cursor - columnOffset < cols;
        }

        @Override
        public MatrixCell<E> next() {
            return new DelegateMatrixCell<>(delegate, columnOffset, rowOffset, cols, row, cursor ++);
        }

        @Override
        public void remove() {

        }

    }

    private static class DelegateMatrixCell<E> implements MatrixCell<E> {

        private final Matrix<E> delegate;
        private final int columnOffset;
        private final int rowOffset;
        private final int cols;
        private final int row;
        private final int col;

        private DelegateMatrixCell(Matrix<E> delegate, int columnOffset, int rowOffset, int cols, int row, int col) {
            this.delegate = delegate;
            this.columnOffset = columnOffset;
            this.rowOffset = rowOffset;
            this.cols = cols;
            this.row = row;
            this.col = col;
        }

        @Override
        public int getRowNumber() {
            return row - rowOffset;
        }

        @Override
        public int getColumnNumber() {
            return col - columnOffset;
        }

        @Override
        public E getValue() {
            return delegate.get(row, col);
        }

        @Override
        public String toString() {
            return String.valueOf(getValue());
        }

    }

}
