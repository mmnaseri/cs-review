package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 11:16 PM)
 */
public interface MatrixFactory {

    <E> Matrix<E> getMatrix(int rows, int columns);

}
