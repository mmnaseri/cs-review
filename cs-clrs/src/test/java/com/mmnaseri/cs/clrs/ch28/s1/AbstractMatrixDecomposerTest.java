package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 7:52 PM)
 */
public class AbstractMatrixDecomposerTest {

    private static class SampleAbstractMatrixDecomposer<E extends Number> extends AbstractMatrixDecomposer<E> {

        private final MatrixDecomposition<E> result;
        private Matrix<E> matrix;

        SampleAbstractMatrixDecomposer(Class<E> type, MatrixDecomposition<E> result) {
            super(type);
            this.result = result;
        }

        @Override
        protected MatrixDecomposition<E> doDecompose(Matrix<E> matrix) {
            this.matrix = matrix;
            return result;
        }

        public Matrix<E> getMatrix() {
            return matrix;
        }

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyIfTheMatrixIsNull() throws Exception {
        new SampleAbstractMatrixDecomposer<>(Integer.class, null).decompose(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = ".*square.*")
    public void testThatItIsUnhappyIfTheMatrixIsNotSquare() throws Exception {
        new SampleAbstractMatrixDecomposer<>(Integer.class, null).decompose(new ArrayMatrix<Integer>(3, 4));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenResultIsNull() throws Exception {
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, null);
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenResultLowerIsNull() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = null;
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenResultUpperIsNull() throws Exception {
        final Matrix<Integer> upper = null;
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testThatItIsUnhappyWhenResultPermutationIsNull() throws Exception {
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = null;
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*lower.*incompatible.*")
    public void testThatItIsUnhappyWhenResultLowerHasIncompatibleRows() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(4, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*lower.*incompatible.*")
    public void testThatItIsUnhappyWhenResultLowerHasIncompatibleColumns() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 4);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*upper.*incompatible.*")
    public void testThatItIsUnhappyWhenResultUpperHasIncompatibleRows() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(4, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*upper.*incompatible.*")
    public void testThatItIsUnhappyWhenResultUpperHasIncompatibleColumns() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 4);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = ".*permutation.*incompatible.*")
    public void testThatItIsUnhappyWhenResultPermutationIsIncompatible() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3, 4);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
    }

    @Test
    public void testThatItDoesNotPassTheOriginalMatrixDown() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, new MatrixDecomposition<>(upper, lower, permutation));
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        decomposer.decompose(matrix);
        assertThat(decomposer.getMatrix() == matrix, is(false));
    }

    @Test
    public void testItReturnsTheResultAsIs() throws Exception {
        final Matrix<Integer> upper = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> lower = new ArrayMatrix<>(3, 3);
        final List<Integer> permutation = Arrays.asList(1, 2, 3);
        final MatrixDecomposition<Integer> decomposition = new MatrixDecomposition<>(upper, lower, permutation);
        final SampleAbstractMatrixDecomposer<Integer> decomposer = new SampleAbstractMatrixDecomposer<>(Integer.class, decomposition);
        final Matrix<Integer> matrix = new ArrayMatrix<>(3, 3);
        final MatrixDecomposition<Integer> result = decomposer.decompose(matrix);
        assertThat(result, is(decomposition));
        assertThat(result == decomposition, is(true));
    }
}