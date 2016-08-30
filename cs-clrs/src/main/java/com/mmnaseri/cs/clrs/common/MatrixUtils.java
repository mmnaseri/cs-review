package com.mmnaseri.cs.clrs.common;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch04.s2.SimpleMatrixMultiplier;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.clrs.common.impl.DelegatingMatrix;
import com.mmnaseri.cs.clrs.common.impl.IdentityMatrix;
import com.mmnaseri.cs.clrs.common.impl.ZeroMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 4:49 PM)
 */
public final class MatrixUtils {

    private static MatrixMultiplier multiplier = new SimpleMatrixMultiplier();

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

    @Quality(Stage.UNTESTED)
    public static <T> Matrix<T> copyOf(Matrix<T> original) {
        final Matrix<T> copy = new ArrayMatrix<>(original.getColumns(), original.getRows());
        copy(original, copy);
        return copy;
    }

    @Quality(Stage.UNTESTED)
    public static <S> Matrix<? extends S> viewOf(Matrix<S> original, int rowOffset, int columnOffset, int rowCount, int columnCount) {
        return new DelegatingMatrix<>(original, rowOffset, columnOffset, rowCount, columnCount);
    }

    @Quality(Stage.UNTESTED)
    public static <S> Matrix<? extends S> viewOf(Matrix<S> original, int rowOffset, int columnOffset) {
        return new DelegatingMatrix<>(original, rowOffset, columnOffset, original.getRows() - rowOffset, original.getColumns() - columnOffset);
    }

    @Quality(Stage.UNTESTED)
    public static <E extends Number> Matrix<E> zero(Class<E> type, int size) {
        return new ZeroMatrix<>(type, size);
    }

    @Quality(Stage.UNTESTED)
    public static <E extends Number> Matrix<E> identity(Class<E> type, int size) {
        return new IdentityMatrix<>(type, size);
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> row(List<E> list) {
        final Matrix<E> matrix = new ArrayMatrix<>(1, list.size());
        for (int i = 0; i < list.size(); i++) {
            E item = list.get(i);
            matrix.set(0, i, item);
        }
        return matrix;
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> column(List<E> list) {
        final Matrix<E> matrix = new ArrayMatrix<>(list.size(), 1);
        for (int i = 0; i < list.size(); i++) {
            E item = list.get(i);
            matrix.set(i, 0, item);
        }
        return matrix;
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> transpose(Matrix<E> original) {
        final Matrix<E> copy = new ArrayMatrix<>(original.getRows(), original.getColumns());
        for (int i = 0; i < original.getRows(); i++) {
            for (int j = 0; j < original.getColumns(); j++) {
                copy.set(j, i, original.get(i, j));
            }
        }
        return copy;
    }

    @Quality(Stage.UNTESTED)
    public static <E extends Number> Matrix<E> multiply(Matrix<E> first, Matrix<E> second) {
        return multiplier.multiply(first, second);
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> removeRow(Matrix<E> matrix, int which) {
        final Matrix<E> result = new ArrayMatrix<>(matrix.getRows() - 1, matrix.getColumns());
        int i = 0;
        for (MatrixRow<E> row : matrix) {
            if (row.getRowNumber() == which) {
                continue;
            }
            int j = 0;
            for (MatrixCell<E> cell : row) {
                result.set(i, j ++, cell.getValue());
            }
            i ++;
        }
        return result;
    }

    @Quality(Stage.UNTESTED)
    public static <E> Matrix<E> removeColumn(Matrix<E> matrix, int which) {
        final Matrix<E> result = new ArrayMatrix<>(matrix.getRows(), matrix.getColumns() - 1);
        int i = 0;
        for (MatrixRow<E> row : matrix) {
            int j = 0;
            for (MatrixCell<E> cell : row) {
                if (cell.getColumnNumber() == which) {
                    continue;
                }
                result.set(i, j ++, cell.getValue());
            }
            i ++;
        }
        return result;
    }

}
