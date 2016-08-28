package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.clrs.common.impl.DelegatingMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 4:49 PM)
 */
public final class MatrixUtils {

    private MatrixUtils() {
        throw new UnsupportedOperationException();
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> asMatrix(E[][] array) {
        int cols = 0;
        for (E[] item : array) {
            cols = Math.max(cols, item.length);
        }
        final Matrix<E> matrix = new ArrayMatrix<>(array.length, cols);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                matrix.set(i, j, array[i][j]);
            }
        }
        return matrix;
    }

    @Quality(Stage.UNTESTED)
    public static <T, S extends T> void copy(Matrix<S> source, Matrix<T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        if (source.getRows() != target.getRows()) {
            throw new IllegalArgumentException("Matrix rows are not compatible");
        }
        if (source.getColumns() != target.getColumns()) {
            throw new IllegalArgumentException("Matrix columns are not compatible");
        }
        for (MatrixRow<S> row : source) {
            for (MatrixCell<S> cell : row) {
                target.set(cell.getRowNumber(), cell.getColumnNumber(), cell.getValue());
            }
        }
    }

    public static <S> Matrix<? extends S> viewOf(Matrix<S> original, int rowOffset, int columnOffset, int rowCount, int columnCount) {
        return new DelegatingMatrix<>(original, rowOffset, columnOffset, rowCount, columnCount);
    }

    public static <S> Matrix<? extends S> viewOf(Matrix<S> original, int rowOffset, int columnOffset) {
        return new DelegatingMatrix<>(original, rowOffset, columnOffset, original.getRows() - rowOffset, original.getColumns() - columnOffset);
    }

}
