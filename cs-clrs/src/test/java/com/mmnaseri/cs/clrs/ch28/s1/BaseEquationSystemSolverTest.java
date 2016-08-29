package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 9:39 PM)
 */
public abstract class BaseEquationSystemSolverTest {

    private EquationSystemSolver<Double> solver;

    protected abstract EquationSystemSolver<Double> getEquationSystemSolver();

    @BeforeMethod
    public void setUp() throws Exception {
        solver = getEquationSystemSolver();
    }

    @DataProvider
    public Object[][] equationSystems() {
        final List<Matrix<Double>> coefficientMatrices = new ArrayList<>();
        final List<List<Double>> valuesLists = new ArrayList<>();
        final List<List<Double>> variableLists = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final Random random = new Random();
            final int size = 1 + random.nextInt(10);
            final Matrix<Double> coefficients = MatrixUtils.copyOf(MatrixUtils.zero(Double.class, size));
            final List<Double> values = new ArrayList<>(size);
            final List<Double> variables = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                variables.add(randomNumber(random));
            }
            for (int j = 0; j < size; j++) {
                double value = 0;
                for (int k = 0; k < size; k++) {
                    final double coefficient = randomNumber(random);
                    coefficients.set(j, k, coefficient);
                    value += coefficient * variables.get(k);
                }
                values.add(value);
            }
            coefficientMatrices.add(coefficients);
            valuesLists.add(values);
            variableLists.add(variables);
        }
        final List<Object[]> result = new ArrayList<>();
        for (int i = 0; i < coefficientMatrices.size(); i++) {
            final Matrix<Double> coefficients = coefficientMatrices.get(i);
            final List<Double> values = valuesLists.get(i);
            final List<Double> variables = variableLists.get(i);
            result.add(new Object[]{coefficients, values, variables});
        }
        return result.toArray(new Object[result.size()][]);
    }

    private double randomNumber(Random random) {
        return random.nextDouble() * (random.nextDouble() > 0.5 ? 1.0 : -1.0);
    }

    @Test(dataProvider = "equationSystems", invocationCount = 10)
    public void testSolutions(Matrix<Double> coefficients, List<Double> values, List<Double> variables) throws Exception {
        final List<Double> solution = solver.solve(coefficients, values);
        assertThat(solution, is(notNullValue()));
        assertThat(solution.size(), is(values.size()));
        for (int i = 0; i < solution.size(); i++) {
            final double found = Math.round(solution.get(i).floatValue() * 100) / 100.0;
            final double variable = Math.round(variables.get(i).floatValue() * 100) / 100.0;
            assertThat(found, is(variable));
        }
    }

}