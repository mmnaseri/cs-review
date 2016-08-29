package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.NumberUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:08 PM)
 */
public abstract class AbstractEquationSystemSolver<E extends Number> implements EquationSystemSolver<E> {

    private final Class<E> type;

    public AbstractEquationSystemSolver(Class<E> type) {
        this.type = type;
    }

    protected Class<E> getType() {
        return type;
    }

    @Override
    public final List<E> solve(Matrix<E> coefficients, List<E> values) {
        Objects.requireNonNull(coefficients);
        Objects.requireNonNull(values);
        if (coefficients.getRows() != coefficients.getColumns()) {
            throw new IllegalArgumentException("Coefficient matrix must be square");
        }
        if (values.size() != coefficients.getColumns()) {
            throw new IllegalArgumentException("Expected the number of values to be compatible with the dimension of the coefficients matrix");
        }
        final List<E> result = doSolve(coefficients, values);
        Objects.requireNonNull(result);
        if (result.size() != values.size()) {
            throw new IllegalStateException("Expected the number of resolved variables to be the same as the initial equations");
        }
        return result;
    }

    protected abstract List<E> doSolve(Matrix<E> coefficients, List<E> values);

    protected E zero() {
        return NumberUtils.zero(getType());
    }

    protected E one() {
        return NumberUtils.one(getType());
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
