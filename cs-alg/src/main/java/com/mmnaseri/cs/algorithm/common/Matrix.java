package com.mmnaseri.cs.algorithm.common;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 10:55 PM)
 */
public interface Matrix<E> extends Iterable<MatrixRow<E>> {

    int getRows();

    int getColumns();

    E get(int row, int col);

    void set(int row, int col, E value);

}
