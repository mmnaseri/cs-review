package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.ch28.s1.AbstractEquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.DecompositionEquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.EquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.MatrixDecomposer;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:16 PM)
 */
@Quality(Stage.TESTED)
public class DecomposerAgnosticEquationSystemSolver<E extends Number> extends AbstractEquationSystemSolver<E> {

    private final EquationSystemSolver<E> delegate;

    public DecomposerAgnosticEquationSystemSolver(Class<E> type, MatrixDecomposer<E> decomposer) {
        super(type);
        delegate = new DecompositionEquationSystemSolver<>(type, decomposer);
    }

    @Override
    protected List<E> doSolve(Matrix<E> coefficients, List<E> values) {
        final Matrix<E> transposedCoefficients = MatrixUtils.transpose(coefficients);
        final Matrix<E> newCoefficients = MatrixUtils.multiply(transposedCoefficients, coefficients);
        final Matrix<E> newValuesMatrix = MatrixUtils.multiply(transposedCoefficients, MatrixUtils.column(values));
        final List<E> newValues = new ArrayList<>(values.size());
        for (int i = 0; i < newValuesMatrix.getRows(); i++) {
            newValues.add(newValuesMatrix.get(i, 0));
        }
        return delegate.solve(newCoefficients, newValues);
    }

}
