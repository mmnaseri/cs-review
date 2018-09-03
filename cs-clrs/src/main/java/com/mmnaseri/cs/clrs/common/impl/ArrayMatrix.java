package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (5/26/15, 11:09 PM)
 */
@Quality(Stage.TESTED)
public class ArrayMatrix<E> extends AbstractMatrix<E> {

    private final Object[][] values;
    private final int rows;
    private final int columns;

    public ArrayMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        values = new Object[rows][columns];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = null;
            }
        }
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    protected E read(int row, int col) {
        if (values[row][col] != null) {
            //noinspection unchecked
            return (E) values[row][col];
        }
        return null;
    }

    @Override
    protected void write(int row, int col, E value) {
        values[row][col] = value;
    }

}
