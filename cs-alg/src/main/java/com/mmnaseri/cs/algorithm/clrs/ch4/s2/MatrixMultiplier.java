package com.mmnaseri.cs.algorithm.clrs.ch4.s2;

import com.mmnaseri.cs.algorithm.common.Matrix;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 10:56 PM)
 */
public interface MatrixMultiplier {

    <E extends Number> Matrix<E> multiply(Matrix<E> first, Matrix<E> second);

}
