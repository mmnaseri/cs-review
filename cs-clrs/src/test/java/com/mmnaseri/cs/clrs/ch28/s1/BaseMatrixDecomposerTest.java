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
 * @since 1.0 (8/28/16, 9:33 PM)
 */
public abstract class BaseMatrixDecomposerTest {

    private MatrixDecomposer<Double> decomposer;

    protected abstract MatrixDecomposer<Double> getDecomposer();

    protected abstract Matrix<Double> generateMatrix(int size, Random random);

    @BeforeMethod
    public void setUp() throws Exception {
        decomposer = getDecomposer();
    }

    @DataProvider
    public Object[][] matrices() {
        final List<Matrix<Double>> matrices = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final Random random = new Random();
            int size = 2 + random.nextInt(18);
            matrices.add(generateMatrix(size, random));
        }
        final List<Object[]> result = new ArrayList<>();
        for (Matrix<Double> matrix : matrices) {
            result.add(new Object[]{matrix});
        }
        return result.toArray(new Object[result.size()][]);
    }

    @Test(dataProvider = "matrices", invocationCount = 10)
    public void testDecomposition(Matrix<Double> matrix) throws Exception {
        final MatrixDecomposition<Double> decomposition = decomposer.decompose(matrix);
        assertThat(decomposition, is(notNullValue()));
        assertThat(decomposition.getPermutation(), is(notNullValue()));
        assertThat(decomposition.getUpper(), is(notNullValue()));
        assertThat(decomposition.getLower(), is(notNullValue()));
        final Matrix<Double> lu = MatrixUtils.multiply(decomposition.getLower(), decomposition.getUpper());
        final Matrix<Double> pa = MatrixUtils.multiply(new PermutationMatrix<>(Double.class, decomposition.getPermutation()), matrix);
        for (int i = 0; i < lu.getRows(); i++) {
            for (int j = 0; j < lu.getColumns(); j++) {
                lu.set(i, j, Math.round(lu.get(i, j) * 100) / 100.);
                pa.set(i, j, Math.round(pa.get(i, j) * 100) / 100.);
            }
        }
        assertThat(lu, is(pa));
    }


}
