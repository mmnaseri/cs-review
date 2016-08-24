package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixCell;
import com.mmnaseri.cs.clrs.common.MatrixRow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/23/16, 8:59 PM)
 */
public class PermutationMatrix implements Matrix<Double> {

    private static final double ON = 1.0;
    private static final int OFF = 0;
    private final List<Integer> permutation;

    public PermutationMatrix(List<Integer> permutation) {
        if (permutation == null) {
            throw new IllegalArgumentException("Permutation cannot be null");
        }
        if (permutation.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a permutation matrix with an empty permutation");
        }
        if (new HashSet<>(permutation).size() != permutation.size()) {
            throw new IllegalArgumentException("Permutation cannot contain duplicate values");
        }
        for (Integer index : permutation) {
            if (index < 0 || index >= permutation.size()) {
                throw new IllegalArgumentException("Permutation indices must be within range");
            }
        }
        this.permutation = new ArrayList<>(permutation);
    }

    @Override
    public int getRows() {
        return permutation.size();
    }

    @Override
    public int getColumns() {
        return permutation.size();
    }

    @Override
    public Double get(int row, int col) {
        return col == permutation.get(row) ? ON : OFF;
    }

    @Override
    public void set(int row, int col, Double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<MatrixRow<Double>> iterator() {
        return new Iterator<MatrixRow<Double>>() {

            private int row = 0;

            @Override
            public boolean hasNext() {
                return row < getRows() - 1;
            }

            @Override
            public MatrixRow<Double> next() {
                return new PermutationMatrixRow(row ++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private final class PermutationMatrixRow implements MatrixRow<Double> {

        private int row;

        private PermutationMatrixRow(int row) {
            this.row = row;
        }

        @Override
        public int getRowNumber() {
            return row;
        }

        @Override
        public Iterator<MatrixCell<Double>> iterator() {
            return new Iterator<MatrixCell<Double>>() {

                private int col = 0;

                @Override
                public boolean hasNext() {
                    return col < getColumns() - 1;
                }

                @Override
                public MatrixCell<Double> next() {
                    return new PermutationMatrixCell(row, col ++);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private final class PermutationMatrixCell implements MatrixCell<Double> {

        private final int row;
        private final int col;

        private PermutationMatrixCell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int getRowNumber() {
            return row;
        }

        @Override
        public int getColumnNumber() {
            return col;
        }

        @Override
        public Double getValue() {
            return get(row, col);
        }

    }

}
