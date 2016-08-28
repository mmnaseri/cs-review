package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 3:50 PM)
 */
@Quality(Stage.UNTESTED)
public class IdentityMatrix<E extends Number> extends AbstractImmutableMatrix<E> {

    private final int size;
    private final Class<E> type;

    public IdentityMatrix(Class<E> type, int size) {
        this.size = size;
        this.type = type;
    }

    @Override
    public int getRows() {
        return size;
    }

    @Override
    public int getColumns() {
        return size;
    }

    @Override
    protected E read(int row, int col) {
        return row == col ? NumberUtils.one(type) : NumberUtils.zero(type);
    }

}
