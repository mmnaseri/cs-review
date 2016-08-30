package com.mmnaseri.cs.clrs.ch28.custom.impl;

import com.mmnaseri.cs.clrs.ch28.custom.DeterminantCalculator;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

import static com.mmnaseri.cs.clrs.common.NumberUtils.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 11:53 AM)
 */
@Quality(Stage.UNTESTED)
public class RecursiveDeterminantCalculator<E extends Number> implements DeterminantCalculator<E> {

    private final Class<E> type;

    public RecursiveDeterminantCalculator(Class<E> type) {
        this.type = type;
    }

    @Override
    public E determinant(Matrix<E> matrix) {
        Objects.requireNonNull(matrix);
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Cannot calculate the determinant of a non-square matrix");
        }
        if (matrix.getRows() == 0) {
            throw new IllegalArgumentException("Cannot calculate the determinant of an empty matrix");
        }
        return calculate(matrix);
    }

    private E calculate(Matrix<E> matrix) {
        final int size = matrix.getRows();
        if (size == 1) {
            return matrix.get(0, 0);
        }
        if (size == 2) {
            return subtract(multiply(matrix.get(0, 0), matrix.get(1, 1)), multiply(matrix.get(0, 1), matrix.get(1, 0)));
        }
        final Matrix<E> headless = MatrixUtils.removeRow(matrix, 0);
        E coefficient = one(type);
        E determinant = zero(type);
        final E minusOne = negate(coefficient);
        for (int i = 0; i < size; i++) {
            determinant = add(determinant, multiply(multiply(coefficient, matrix.get(0, i)), calculate(MatrixUtils.removeColumn(headless, i))));
            coefficient = multiply(coefficient, minusOne);
        }
        return determinant;
    }

}
