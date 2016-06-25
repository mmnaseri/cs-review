package com.mmnaseri.cs.clrs.ch04.s2;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMatrixMultiplierTest {

    /**
     *
     * |  2  3  |                    | 12 11  8 |
     * |  0  4  |  * |  6  1  1  | = |  0 12  8 |
     * | -1  5  |    |  0  3  2  |   | -6 14  9 |
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
        assertThat(multiplication.getColumns(), is(3));
        assertThat(multiplication.getRows(), is(3));
        assertThat(multiplication.get(0, 0), is(12));
        assertThat(multiplication.get(0, 1), is(11));
        assertThat(multiplication.get(0, 2), is(8));
        assertThat(multiplication.get(1, 0), is(0));
        assertThat(multiplication.get(1, 1), is(12));
        assertThat(multiplication.get(1, 2), is(8));
        assertThat(multiplication.get(2, 0), is(-6));
        assertThat(multiplication.get(2, 1), is(14));
        assertThat(multiplication.get(2, 2), is(9));
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incompatible matrix dimensions")
    public void testIncompatibleMatrices() throws Exception {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> second = new ArrayMatrix<>(4, 4);
        final SimpleMatrixMultiplier multiplier = new SimpleMatrixMultiplier();
        multiplier.multiply(first, second);
    }
}