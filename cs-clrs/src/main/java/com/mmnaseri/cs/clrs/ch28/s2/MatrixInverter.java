package com.mmnaseri.cs.clrs.ch28.s2;

import com.mmnaseri.cs.clrs.common.Matrix;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:24 PM)
 */
public interface MatrixInverter<E extends Number> {

    Matrix<E> invert(Matrix<E> original);

}
