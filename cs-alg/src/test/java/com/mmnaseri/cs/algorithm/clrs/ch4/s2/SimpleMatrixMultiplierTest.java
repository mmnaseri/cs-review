package com.mmnaseri.cs.algorithm.clrs.ch4.s2;

import com.mmnaseri.cs.algorithm.common.Matrix;
import com.mmnaseri.cs.algorithm.common.impl.ArrayMatrix;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMatrixMultiplierTest {

    /**
     *
     * |  2  3  |
     * |  0  4  |  * |  6  1  1  | =  |  11   27 |
     * | -1  5  |    |  0  3  2  |    |  -2   22 |
     *
     */
    @Test
    public void testMultiplication() throws Exception {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 2);
        first.set(0, 0, 2);
        first.set(0, 1, 3);
        first.set(1, 0, 0);
        first.set(1, 1, 4);
        first.set(2, 0, -1);
        first.set(2, 1, 5);
        final Matrix<Integer> second = new ArrayMatrix<>(2, 3);
        second.set(0, 0, 6);
        second.set(0, 1, 1);
        second.set(0, 2, 1);
        second.set(1, 0, 0);
        second.set(1, 1, 3);
        second.set(1, 2, 2);
        final SimpleMatrixMultiplier multiplier = new SimpleMatrixMultiplier();
        final Matrix<Integer> multiplication = multiplier.multiply(first, second);
        assertThat(multiplication.getColumns(), is(2));
        assertThat(multiplication.getRows(), is(2));
        assertThat(multiplication.get(0, 0), is(11));
        assertThat(multiplication.get(0, 1), is(27));
        assertThat(multiplication.get(1, 0), is(-2));
        assertThat(multiplication.get(1, 1), is(22));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incompatible matrix dimensions")
    public void testIncompatibleMatrices() throws Exception {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> second = new ArrayMatrix<>(4, 4);
        final SimpleMatrixMultiplier multiplier = new SimpleMatrixMultiplier();
        multiplier.multiply(first, second);
    }
}