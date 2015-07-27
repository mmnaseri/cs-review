package com.mmnaseri.cs.clrs.common.impl;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 11:17 PM)
 */
@Quality(Stage.UNTESTED)
public class ArrayMatrixFactory implements MatrixFactory {

    @Override
    public <E> Matrix<E> getMatrix(int rows, int columns) {
        return new ArrayMatrix<>(rows, columns);
    }

}
