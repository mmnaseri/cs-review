package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.ch28.s1.DecompositionEquationSystemSolver;
import com.mmnaseri.cs.clrs.ch28.s1.LUPMatrixDecomposer;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:25 PM)
 */
@Quality(Stage.UNTESTED)
public class EquationSystemMatrixInverter<E extends Number> extends AbstractMatrixInverter<E> {

    private final DecompositionEquationSystemSolver<E> solver;

    public EquationSystemMatrixInverter(Class<E> type) {
        this(type, new ArrayMatrixFactory());
    }

    public EquationSystemMatrixInverter(Class<E> type, MatrixFactory factory) {
        super(type, factory);
        solver = new DecompositionEquationSystemSolver<>(type, new LUPMatrixDecomposer<>(type));
    }

    @Override
    protected Matrix<E> calculateInverse(Matrix<E> original) {
        final Matrix<E> inverse = createMatrix(original);
        final List<E> values = new ArrayList<>();
        for (int i = 0; i < original.getRows(); i++) {
            values.add(zero());
        }
        for (int i = 0; i < original.getColumns(); i++) {
            if (i > 0) {
                values.set(i - 1, zero());
            }
            values.set(i, one());
            final List<E> column = solver.solve(original, values);
            for (int j = 0; j < column.size(); j++) {
                inverse.set(i, j, column.get(j));
            }
        }
        return inverse;
    }

}
