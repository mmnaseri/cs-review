package com.mmnaseri.cs.clrs.ch28.s1;

import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 5:36 PM)
 */
public interface MatrixDecomposer<E extends Number> {

    MatrixDecomposition<E> decompose(Matrix<E> matrix);

}
