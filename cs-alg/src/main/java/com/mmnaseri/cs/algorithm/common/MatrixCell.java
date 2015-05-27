package com.mmnaseri.cs.algorithm.common;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 11:12 PM)
 */
public interface MatrixCell<E> {

    int getRowNumber();

    int getColumnNumber();

    E getValue();

}
