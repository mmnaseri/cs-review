package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:32 PM)
 */
@Quality(Stage.UNTESTED)
public abstract class AbstractMatrixInverter<E extends Number> implements MatrixInverter<E> {

    protected final Class<E> type;
    private final MatrixFactory factory;
    private final E zero;
    private final E one;

    public AbstractMatrixInverter(Class<E> type, MatrixFactory factory) {
        this.type = type;
        one = NumberUtils.one(getType());
        this.factory = factory;
        zero = NumberUtils.zero(getType());
    }

    @Override
    public Matrix<E> invert(Matrix<E> original) {
        Objects.requireNonNull(original);
        if (original.getRows() != original.getColumns()) {
            throw new IllegalArgumentException("Cannot calculate the inverse of a non-square matrix");
        }
        final Matrix<E> inverse = calculateInverse(MatrixUtils.copyOf(original));
        Objects.requireNonNull(inverse);
        if (inverse.getRows() != inverse.getColumns()) {
            throw new IllegalStateException("Expected inverse to be square");
        }
        if (inverse.getRows() != original.getRows()) {
            throw new IllegalStateException("Expected inverse to be of the same dimensions as the original");
        }
        return inverse;
    }

    protected abstract Matrix<E> calculateInverse(Matrix<E> original);

    public Class<E> getType() {
        return type;
    }

    protected E one() {
        return one;
    }

    protected E zero() {
        return zero;
    }

    protected Matrix<E> createMatrix(Matrix<E> original) {
        return factory.getMatrix(original.getRows(), original.getColumns());
    }
}
