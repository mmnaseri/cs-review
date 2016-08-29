package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;

import java.util.List;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 5:36 PM)
 */
public class MatrixDecomposition<E extends Number> {

    private final Matrix<E> upper;
    private final Matrix<E> lower;
    private final List<Integer> permutation;

    public MatrixDecomposition(Matrix<E> upper, Matrix<E> lower, List<Integer> permutation) {
        this.upper = upper;
        this.lower = lower;
        this.permutation = permutation;
    }

    public Matrix<E> getLower() {
        return lower;
    }

    public Matrix<E> getUpper() {
        return upper;
    }

    public List<Integer> getPermutation() {
        return permutation;
    }

    @Override
    public String toString() {
        return "Upper:\n" + upper + "\nLower:\n" + lower + "\nPermutation:\n" + new PermutationMatrix(permutation);
    }

}
