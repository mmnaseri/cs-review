package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;

import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:08 PM)
 */
public abstract class AbstractEquationSystemSolver implements EquationSystemSolver {

    @Override
    public final List<Double> solve(Matrix<Double> coefficients, List<Double> values) {
        Objects.requireNonNull(coefficients);
        Objects.requireNonNull(values);
        if (coefficients.getRows() != coefficients.getColumns()) {
            throw new IllegalArgumentException("Coefficient matrix must be square");
        }
        if (values.size() != coefficients.getColumns()) {
            throw new IllegalArgumentException("Expected the number of values to be compatible with the dimension of the coefficients matrix");
        }
        final List<Double> result = doSolve(coefficients, values);
        Objects.requireNonNull(result);
        if (result.size() != values.size()) {
            throw new IllegalStateException("Expected the number of resolved variables to be the same as the initial equations");
        }
        return result;
    }

    protected abstract List<Double> doSolve(Matrix<Double> coefficients, List<Double> values);

}
