package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 5:37 PM)
 */
public abstract class AbstractMatrixDecomposer<E extends Number> implements MatrixDecomposer<E> {

    private final Class<E> type;
    private final E zero;
    private final E one;

    public AbstractMatrixDecomposer(Class<E> type) {
        this.type = type;
        zero = NumberUtils.zero(type);
        one = NumberUtils.one(type);
    }

    public Class<E> getType() {
        return type;
    }

    @Override
    public final MatrixDecomposition<E> decompose(Matrix<E> matrix) {
        Objects.requireNonNull(matrix);
        if (matrix.getRows() != matrix.getColumns()) {
            throw new IllegalArgumentException("Cannot decompose a matrix that is not square");
        }
        final MatrixDecomposition<E> result = doDecompose(MatrixUtils.copyOf(matrix));
        Objects.requireNonNull(result);
        Objects.requireNonNull(result.getLower());
        Objects.requireNonNull(result.getUpper());
        if (matrix.getColumns() != result.getLower().getColumns() || matrix.getRows() != result.getLower().getRows()) {
            throw new IllegalStateException("Resulting lower matrix is incompatible with the original matrix");
        }
        if (matrix.getColumns() != result.getUpper().getColumns() || matrix.getRows() != result.getUpper().getRows()) {
            throw new IllegalStateException("Resulting upper matrix is incompatible with the original matrix");
        }
        return result;
    }

    protected abstract MatrixDecomposition<E> doDecompose(Matrix<E> matrix);

    protected E zero() {
        return zero;
    }

    protected E one() {
        return one;
    }

    protected E add(E first, E second) {
        return NumberUtils.add(first, second);
    }

    protected E subtract(E first, E second) {
        return NumberUtils.subtract(first, second);
    }

    protected E divide(E first, E second) {
        return NumberUtils.divide(first, second);
    }

    protected E multiply(E first, E second) {
        return NumberUtils.multiply(first, second);
    }

}
