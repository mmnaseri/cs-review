package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 5:43 PM)
 */
@Quality(Stage.TESTED)
public class LUPMatrixDecomposer<E extends Number> extends AbstractMatrixDecomposer<E> {

    public LUPMatrixDecomposer(Class<E> type) {
        super(type);
    }

    @Override
    protected MatrixDecomposition<E> doDecompose(Matrix<E> matrix) {
        final int size = matrix.getRows();
        final List<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            permutation.add(i);
        }
        for (int k = 0; k < size; k++) {
            E high = zero();
            int pivot = -1;
            for (int i = k; i < size; i++) {
                if (NumberUtils.compare(NumberUtils.abs(matrix.get(i, k)), high) > 0) {
                    high = NumberUtils.abs(matrix.get(i, k));
                    pivot = i;
                }
            }
            if (high.equals(zero())) {
                throw new IllegalStateException("Cannot compute a decomposition for a sigular matrix");
            }
            exchange(permutation, k, pivot);
            for (int i = 0; i < size; i++) {
                final E temp = matrix.get(k, i);
                matrix.set(k, i, matrix.get(pivot, i));
                matrix.set(pivot, i, temp);
            }
            for (int i = k + 1; i < size; i++) {
                matrix.set(i, k, divide(matrix.get(i, k), matrix.get(k, k)));
                for (int j = k + 1; j < size; j++) {
                    matrix.set(i, j, subtract(matrix.get(i, j), multiply(matrix.get(i, k), matrix.get(k, j))));
                }
            }
        }
        final Matrix<E> upper = MatrixUtils.copyOf(matrix);
        final Matrix<E> lower = MatrixUtils.copyOf(matrix);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i > j) {
                    upper.set(i, j, zero());
                } else if (i != j) {
                    lower.set(i, j, zero());
                } else {
                    lower.set(i, j, one());
                }
            }
        }
        return new MatrixDecomposition<>(upper, lower, permutation);
    }

    private void exchange(List<Integer> permutation, int k, int pivot) {
        final Integer temp = permutation.get(pivot);
        permutation.set(pivot, permutation.get(k));
        permutation.set(k, temp);
    }

}
