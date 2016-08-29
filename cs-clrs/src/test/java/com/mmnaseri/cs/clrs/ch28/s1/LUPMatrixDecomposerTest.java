package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;

import java.util.Random;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 8:12 PM)
 */
public class LUPMatrixDecomposerTest extends BaseMatrixDecomposerTest {

    @Override
    protected MatrixDecomposer<Double> getDecomposer() {
        return new LUPMatrixDecomposer<>(Double.class);
    }

    @Override
    protected Matrix<Double> generateMatrix(int size, Random random) {
        final Matrix<Double> matrix = new ArrayMatrix<>(size, size);
        for (int j = 0; j < size; j++) {
            for (int k = 0; k < size; k++) {
                matrix.set(j, k, (random.nextDouble() > 0.5 ? -1. : .1) * random.nextDouble() * size * size);
            }
        }
        return matrix;
    }

}