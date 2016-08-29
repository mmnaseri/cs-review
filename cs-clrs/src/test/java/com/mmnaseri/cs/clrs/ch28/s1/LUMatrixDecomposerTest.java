package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixUtils;

import java.util.Random;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 8:44 PM)
 */
public class LUMatrixDecomposerTest extends BaseMatrixDecomposerTest {

    @Override
    protected MatrixDecomposer<Double> getDecomposer() {
        return new LUMatrixDecomposer<>(Double.class);
    }

    @Override
    protected Matrix<Double> generateMatrix(int size, Random random) {
        final Matrix<Double> seed = randomLowerTriangular(size, random);
        return MatrixUtils.multiply(seed, MatrixUtils.transpose(seed));
    }

    private Matrix<Double> randomLowerTriangular(int size, Random random) {
        final Matrix<Double> matrix = MatrixUtils.copyOf(MatrixUtils.identity(Double.class, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i - 1; j++) {
                matrix.set(i, j, 1 + random.nextDouble() * size * size);
            }
        }
        for (int i = 0; i < size; i++) {
            matrix.set(i, i, 1 + random.nextDouble() * size * size);
        }
        return matrix;
    }

}