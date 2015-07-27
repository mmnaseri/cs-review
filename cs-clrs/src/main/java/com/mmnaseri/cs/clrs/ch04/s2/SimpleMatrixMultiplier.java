package com.mmnaseri.cs.clrs.ch04.s2;

import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 10:56 PM)
 */
@Quality(Stage.TESTED)
public class SimpleMatrixMultiplier implements MatrixMultiplier {

    private final MatrixFactory factory;

    public SimpleMatrixMultiplier() {
        this(new ArrayMatrixFactory());
    }

    public SimpleMatrixMultiplier(MatrixFactory factory) {
        this.factory = factory;
    }

    @Override
    public <E extends Number> Matrix<E> multiply(Matrix<E> first, Matrix<E> second) {
        if (first.getRows() != second.getColumns()) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        final Matrix<E> result = factory.getMatrix(second.getRows(), first.getColumns());
        for (int j = 0; j < first.getColumns(); j ++) {
            for (int i = 0; i < second.getRows(); i ++) {
                E value = null;
                for (int k = 0; k < first.getRows(); k ++) {
                    final E firstValue = first.get(k, j);
                    final E secondValue = second.get(i, k);
                    final E multiplication = NumberUtils.multiply(firstValue, secondValue);
                    value = NumberUtils.add(value, multiplication);
                }
                result.set(i, j, value);
            }
        }
        return result;
    }

}

