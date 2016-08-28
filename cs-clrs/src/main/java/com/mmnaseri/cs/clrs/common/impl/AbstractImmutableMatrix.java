package com.mmnaseri.cs.clrs.common.impl;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 4:13 PM)
 */
public abstract class AbstractImmutableMatrix<E> extends AbstractMatrix<E> {

    @Override
    protected void write(int row, int col, E value) {
        throw new UnsupportedOperationException();
    }

}
