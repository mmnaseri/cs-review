package com.mmnaseri.cs.algorithm.common.impl;

import com.mmnaseri.cs.algorithm.common.Matrix;
import com.mmnaseri.cs.algorithm.common.MatrixCell;
import com.mmnaseri.cs.algorithm.common.MatrixRow;

import java.util.Iterator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 11:09 PM)
 */
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
        if (values[row][col] != null) {
            //noinspection unchecked
            return (E) values[row][col];
        }
        return null;
    }

    @Override
    public void set(int row, int col, E value) {
        values[row][col] = value;
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
        };
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
            };
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
    }


}
