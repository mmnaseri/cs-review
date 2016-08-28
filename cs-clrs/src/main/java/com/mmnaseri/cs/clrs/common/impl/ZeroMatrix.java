package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 4:47 PM)
 */
@Quality(Stage.UNTESTED)
public class ZeroMatrix<E extends Number> extends AbstractImmutableMatrix<E> {

    private final Class<E> type;
    private final int size;

    public ZeroMatrix(Class<E> type, int size) {
        this.type = type;
        this.size = size;
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
        return NumberUtils.zero(type);
    }

}
