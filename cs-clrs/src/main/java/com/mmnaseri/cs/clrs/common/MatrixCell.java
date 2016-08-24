package com.mmnaseri.cs.clrs.common;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 11:12 PM)
 */
public interface MatrixCell<E> {

    int getRowNumber();

    int getColumnNumber();

    E getValue();

}
