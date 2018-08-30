package com.mmnaseri.cs.clrs.ch04.s2;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.qa.annotation.Complexity;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 10:56 PM)
 */
@Quality(Stage.TESTED)
public class SimpleMatrixMultiplier implements MatrixMultiplier {

    private final MatrixFactory factory;

    public SimpleMatrixMultiplier() {
        this(new ArrayMatrixFactory());
    }

    public SimpleMatrixMultiplier(MatrixFactory factory) {
        this.factory = factory;
    }

    @Complexity(value = "O(N^3)")
    @Override
    public <E extends Number> Matrix<E> multiply(Matrix<E> first, Matrix<E> second) {
        if (first.getColumns() != second.getRows()) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        final Matrix<E> result = factory.getMatrix(first.getRows(), second.getColumns());
        for (int row = 0; row < first.getRows(); row ++) {
            for (int column = 0; column < second.getColumns(); column ++) {
                multiplyRowByColumn(first, second, result, row, column);
            }
        }
        return result;
    }

    public static <E extends Number> void multiplyRowByColumn(Matrix<E> first, Matrix<E> second, Matrix<E> result, int row, int column) {
        E value = null;
        for (int i = 0; i < first.getColumns(); i++) {
            final E firstValue = first.get(row, i);
            final E secondValue = second.get(i, column);
            final E multiplication = NumberUtils.multiply(firstValue, secondValue);
            value = NumberUtils.add(value, multiplication);
        }
        result.set(row, column, value);
    }

}

