package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.ch28.custom.InvertibleMatrixGenerator;
import com.mmnaseri.cs.clrs.ch28.custom.impl.SimpleInvertibleMatrixGenerator;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:36 PM)
 */
public abstract class BaseMatrixInverterTest {

    private MatrixInverter<Double> inverter;

    protected abstract MatrixInverter<Double> getInverter();

    @BeforeMethod
    public void setUp() throws Exception {
        inverter = getInverter();
    }

    @DataProvider
    public Object[][] invertibleMatrices() {
        final InvertibleMatrixGenerator<?> generator = new SimpleInvertibleMatrixGenerator<>(Double.class);
        final List<Matrix<?>> matrices = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int size = 4 + random.nextInt(16);
            matrices.add(generator.generate(size));
        }
        final List<Object[]> result = new ArrayList<>();
        for (Matrix<?> matrix : matrices) {
            result.add(new Object[]{matrix});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "invertibleMatrices", invocationCount = 10)
    public void testInversion(Matrix<Double> matrix) throws Exception {
        final Matrix<Double> inverse = inverter.invert(matrix);
        assertThat(inverse, is(notNullValue()));
        assertThat(inverse.getRows(), is(matrix.getRows()));
        assertThat(inverse.getColumns(), is(matrix.getColumns()));
        final Matrix<Double> identity = MatrixUtils.identity(Double.class, inverse.getRows());
        final Matrix<Double> multiplication = MatrixUtils.multiply(matrix, inverse);
        for (int i = 0; i < identity.getRows(); i++) {
            for (int j = 0; j < identity.getColumns(); j++) {
                multiplication.set(i, j, Math.round(identity.get(i, j) * 100.0) / 100.0);
            }
        }
        assertThat(multiplication, is(identity));
    }

}