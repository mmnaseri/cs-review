package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:08 PM)
 */
@Quality(Stage.UNTESTED)
public class DecompositionEquationSystemSolver<E extends Number> extends AbstractEquationSystemSolver<E> {

    private final MatrixDecomposer<E> decomposer;

    public DecompositionEquationSystemSolver(Class<E> type, MatrixDecomposer<E> decomposer) {
        super(type);
        this.decomposer = decomposer;
    }

    @Override
    protected List<E> doSolve(Matrix<E> coefficients, List<E> values) {
        final MatrixDecomposition<E> decomposition = decomposer.decompose(coefficients);
        return lupSolve(decomposition, values);
    }

    private List<E> lupSolve(MatrixDecomposition<E> decomposition, List<E> values) {
        final int size = values.size();
        final List<E> result = new ArrayList<>(size);
        final List<E> interim = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(zero());
            interim.add(zero());
        }
        for (int i = 0; i < size; i++) {
            E summation = zero();
            for (int j = 0; j < i; j++) {
                summation = add(summation, multiply(decomposition.getLower().get(i, j), interim.get(j)));
            }
            interim.set(i, subtract(values.get(decomposition.getPermutation().get(i)), summation));
        }
        for (int i = size - 1; i >= 0; i --) {
            E summation = zero();
            for (int j = i; j < size; j++) {
                summation = add(summation, multiply(decomposition.getUpper().get(i, j), result.get(j)));
            }
            result.set(i, divide(subtract(interim.get(i), summation), decomposition.getUpper().get(i, i)));
        }
        return result;
    }

}
