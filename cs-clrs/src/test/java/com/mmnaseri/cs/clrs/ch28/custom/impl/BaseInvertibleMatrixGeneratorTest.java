package com.mmnaseri.cs.clrs.ch28.custom.impl;

import com.mmnaseri.cs.clrs.ch28.custom.DeterminantCalculator;
import com.mmnaseri.cs.clrs.ch28.custom.InvertibleMatrixGenerator;
import com.mmnaseri.cs.clrs.common.Matrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 1:52 PM)
 */
public abstract class BaseInvertibleMatrixGeneratorTest {

    protected abstract InvertibleMatrixGenerator<Double> getGenerator();

    @DataProvider
    public Object[][] matrices() {
        final InvertibleMatrixGenerator<Double> generator = getGenerator();
        final List<Matrix<Double>> matrices = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            matrices.add(generator.generate(i));
        }
        final List<Object[]> result = new ArrayList<>();
        for (Matrix<Double> matrix : matrices) {
            result.add(new Object[]{matrix});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "matrices", invocationCount = 10)
    public void testDeterminantIsNotZero(Matrix<Double> matrix) throws Exception {
        final DeterminantCalculator<Double> calculator = new RecursiveDeterminantCalculator<>(Double.class);
        final Double determinant = calculator.determinant(matrix);
        assertThat(determinant, is(not(0D)));
    }

    @Test(dataProvider = "matrices", invocationCount = 10)
    public void testMatricesAreSquare(Matrix<Double> matrix) throws Exception {
        assertThat(matrix, is(notNullValue()));
        assertThat(matrix.getRows(), is(greaterThan(0)));
        assertThat(matrix.getColumns(), is(greaterThan(0)));
        assertThat(matrix.getRows(), is(matrix.getColumns()));
    }

}