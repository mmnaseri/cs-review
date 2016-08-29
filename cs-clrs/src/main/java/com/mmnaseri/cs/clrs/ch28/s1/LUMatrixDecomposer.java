package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 5:37 PM)
 */
@Quality(Stage.TESTED)
public class LUMatrixDecomposer<E extends Number> extends AbstractMatrixDecomposer<E> {

    public LUMatrixDecomposer(Class<E> type) {
        super(type);
    }

    @Override
    protected MatrixDecomposition<E> doDecompose(Matrix<E> matrix) {
        final int size = matrix.getRows();
        final Matrix<E> lower = MatrixUtils.copyOf(MatrixUtils.zero(getType(), size));
        final Matrix<E> upper = MatrixUtils.copyOf(MatrixUtils.zero(getType(), size));
        final List<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            lower.set(i, i, one());
            permutation.add(i);
        }
        for (int k = 0; k < size; k++) {
            if (zero().equals(matrix.get(k, k))) {
                throw new IllegalStateException("Cannot compute LU decomposition without pivoting for this matrix. Use LUP decomposition instead.");
            }
            upper.set(k, k, matrix.get(k, k));
            for (int i = k + 1; i < size; i++) {
                lower.set(i, k, divide(matrix.get(i, k), upper.get(k, k)));
                upper.set(k, i, matrix.get(k, i));
            }
            for (int i = k + 1; i < size; i++) {
                for (int j = k + 1; j < size; j++) {
                    matrix.set(i, j, subtract(matrix.get(i, j), multiply(lower.get(i, k), upper.get(k, j))));
                }
            }
        }
        return new MatrixDecomposition<>(upper, lower, permutation);
    }


}
