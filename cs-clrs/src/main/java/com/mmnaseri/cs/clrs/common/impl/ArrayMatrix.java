package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 11:09 PM)
 */
@Quality(Stage.UNTESTED)
public class ArrayMatrix<E> implements Matrix<E> {

    private final Object[][] values;
    private final int rows;
    private final int columns;

    public ArrayMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new Object[rows][columns];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = null;
            }
        }
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public E get(int row, int col) {
        checkIndices(row, col);
        if (values[row][col] != null) {
            //noinspection unchecked
            return (E) values[row][col];
        }
        return null;
    }

    @Override
    public void set(int row, int col, E value) {
        checkIndices(row, col);
        values[row][col] = value;
    }

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
        return new Iterator<MatrixRow<E>>() {

            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < rows;
            }

            @Override
            public MatrixRow<E> next() {
                return new ArrayMatrixRow(cursor ++);
            }

            @Override
            public void remove() {
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (MatrixRow<E> row : this) {
            builder.append(row).append("\n");
        }
        return builder.toString().trim();
    }

    private class ArrayMatrixRow implements MatrixRow<E> {

        private final int rowNumber;

        private ArrayMatrixRow(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        @Override
        public int getRowNumber() {
            return rowNumber;
        }

        @Override
        public Iterator<MatrixCell<E>> iterator() {
            return new Iterator<MatrixCell<E>>() {

                private int cursor = 0;

                @Override
                public boolean hasNext() {
                    return cursor < columns;
                }

                @Override
                public MatrixCell<E> next() {
                    return new ArrayMatrixCell(rowNumber, cursor ++);
                }

                @Override
                public void remove() {

                }
            };
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

    private class ArrayMatrixCell implements MatrixCell<E> {

        private final int rowNumber;
        private final int columnNumber;

        private ArrayMatrixCell(int rowNumber, int columnNumber) {
            this.rowNumber = rowNumber;
            this.columnNumber = columnNumber;
        }

        @Override
        public int getRowNumber() {
            return rowNumber;
        }

        @Override
        public int getColumnNumber() {
            return columnNumber;
        }

        @Override
        public E getValue() {
            return ArrayMatrix.this.get(rowNumber, columnNumber);
        }

        @Override
        public String toString() {
            return String.valueOf(getValue());
        }
    }


}
