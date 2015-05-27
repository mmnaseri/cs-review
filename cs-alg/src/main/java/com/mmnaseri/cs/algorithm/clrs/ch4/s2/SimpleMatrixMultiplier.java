package com.mmnaseri.cs.algorithm.clrs.ch4.s2;

import com.mmnaseri.cs.algorithm.common.*;
import com.mmnaseri.cs.algorithm.common.impl.ArrayMatrix;
import com.mmnaseri.cs.algorithm.common.impl.ArrayMatrixFactory;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (5/26/15, 10:56 PM)
 */
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

    public static void main(String[] args) {
        final ArrayMatrix<Integer> first = new ArrayMatrix<>(3, 2);
        first.set(0, 0, 2);
        first.set(0, 1, 3);
        first.set(1, 0, 0);
        first.set(1, 1, 4);
        first.set(2, 0, -1);
        first.set(2, 1, 5);
        final ArrayMatrix<Integer> second = new ArrayMatrix<>(2, 3);
        second.set(0, 0, 6);
        second.set(0, 1, 1);
        second.set(0, 2, 1);
        second.set(1, 0, 0);
        second.set(1, 1, 3);
        second.set(1, 2, 2);
        final SimpleMatrixMultiplier multiplier = new SimpleMatrixMultiplier();
        final Matrix<Integer> multiplication = multiplier.multiply(first, second);
        for (MatrixRow<Integer> row : multiplication) {
            for (MatrixCell<Integer> cell : row) {
                System.out.print(cell.getValue() + "\t");
            }
            System.out.println();
        }
    }

}

/**
 *
 * |  2  3  |
 * |  0  4  |  * |  6  1  1  | =  |  11   27 |
 * | -1  5  |    |  0  3  2  |    |  -2   22 |
 *
 */