package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 7:40 PM)
 */
public class AbstractEquationSystemSolverTest {

    private static class SampleAbstractEquationSystemSolver<E extends Number> extends AbstractEquationSystemSolver<E> {

        private List<E> result;
        private Matrix<E> coefficients;
        private List<E> values;

        public SampleAbstractEquationSystemSolver(Class<E> type, List<E> result) {
            super(type);
            this.result = result;
        }

        @Override
        protected List<E> doSolve(Matrix<E> coefficients, List<E> values) {
            this.coefficients = coefficients;
            this.values = values;
            return result;
        }

        public List<E> getValues() {
            return values;
        }

        public Matrix<E> getCoefficients() {
            return coefficients;
        }

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenTypeIsNull() throws Exception {
        new SampleAbstractEquationSystemSolver<>(null, null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenCoefficientsAreNull() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        solver.solve(null, Collections.<Integer>emptyList());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenValuesAreNull() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        solver.solve(new ArrayMatrix<Integer>(0, 0), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*square.*")
    public void testThatItIsUnhappyIfCoefficientsIsNotSquare() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        solver.solve(new ArrayMatrix<Integer>(3, 2), Collections.<Integer>emptyList());
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*compatible.*")
    public void testThatItComplainsIfValueIsNotCompatibleWithCoefficients() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        solver.solve(new ArrayMatrix<Integer>(4, 4), Arrays.asList(1, 2, 3));
    }

    @Test
    public void testThatItReturnsAnEmptyListWhenInputsAreEmpty() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        final List<Integer> solution = solver.solve(new ArrayMatrix<Integer>(0, 0), Collections.<Integer>emptyList());
        assertThat(solution, is(Matchers.<Integer>empty()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItComplainsWhenSolutionIsNull() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, null);
        solver.solve(new ArrayMatrix<Integer>(3, 3), Arrays.asList(1, 2, 3));
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*variables.*")
    public void testThatItComplainsWhenSolutionIsIncompatible() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, Arrays.asList(4, 5, 6, 7));
        solver.solve(new ArrayMatrix<Integer>(3, 3), Arrays.asList(1, 2, 3));
    }

    @Test
    public void testThatItReturnsTheOriginalResultIfItIsCompatible() throws Exception {
        final List<Integer> result = Arrays.asList(1, 2, 3);
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, result);
        final List<Integer> solution = solver.solve(new ArrayMatrix<Integer>(3, 3), Arrays.asList(1, 2, 3));
        assertThat(solution, is(result));
        assertThat(solution == result, is(true));
    }

    @Test
    public void testThatItPassesACopyOfTheInputDown() throws Exception {
        final SampleAbstractEquationSystemSolver<Integer> solver = new SampleAbstractEquationSystemSolver<>(Integer.class, Arrays.asList(1, 2, 3));
        final Matrix<Integer> coefficients = new ArrayMatrix<>(3, 3);
        final List<Integer> values = Arrays.asList(4, 5, 6);
        solver.solve(coefficients, values);
        assertThat(solver.getCoefficients() == coefficients, is(false));
        assertThat(solver.getValues() == values, is(false));
    }

}