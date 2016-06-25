package com.mmnaseri.cs.clrs.ch04.s2;

import com.mmnaseri.cs.clrs.common.*;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.clrs.common.impl.DelegatingMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * This is Strassen's algorithm. Refer to CLRS 3rd edition, page 77
 *
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 11:12)
 */
@Quality(value = Stage.TESTED, explanation = "It is only supposed to work where matrix dimensions are only divisible by 2")
public class RecursiveMatrixMultiplier implements MatrixMultiplier {

    private final MatrixFactory factory;

    public RecursiveMatrixMultiplier() {
        this(new ArrayMatrixFactory());
    }

    public RecursiveMatrixMultiplier(MatrixFactory factory) {
        this.factory = factory;
    }

    @Override
    public <E extends Number> Matrix<E> multiply(Matrix<E> first, Matrix<E> second) {
        if (first.getColumns() != first.getRows()) {
            throw new IllegalArgumentException("First matrix isn't square: " + first.getRows() + "x" + first.getColumns());
        }
        if (second.getColumns() != second.getRows()) {
            throw new IllegalArgumentException("Second matrix isn't square: " + second.getRows() + "x" + second.getColumns());
        }
        if ((first.getColumns() & (first.getColumns() - 1)) != 0) {
            throw new IllegalArgumentException("The first matrix's dimension is not a power of two");
        }
        if ((second.getColumns() & (second.getColumns() - 1)) != 0) {
            throw new IllegalArgumentException("The second matrix's dimension is not a power of two");
        }
        if (first.getRows() != second.getColumns()) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        final int size = first.getColumns();
        final Matrix<E> result = factory.getMatrix(size, size);
        if (size == 1) {
            result.set(0, 0, NumberUtils.multiply(first.get(0, 0), second.get(0, 0)));
        } else {
            final int newSize = size / 2;
            //Partitioning the first matrix
            final DelegatingMatrix<E> firstTopLeft = new DelegatingMatrix<>(first, 0, 0, newSize, newSize);
            final DelegatingMatrix<E> firstTopRight = new DelegatingMatrix<>(first, 0, newSize, newSize, size - newSize);
            final DelegatingMatrix<E> firstBottomLeft = new DelegatingMatrix<>(first, newSize, 0, size - newSize, newSize);
            final DelegatingMatrix<E> firstBottomRight = new DelegatingMatrix<>(first, newSize, newSize, size - newSize, size - newSize);
            //Partitioning the second matrix
            final DelegatingMatrix<E> secondTopLeft = new DelegatingMatrix<>(second, 0, 0, newSize, newSize);
            final DelegatingMatrix<E> secondTopRight = new DelegatingMatrix<>(second, 0, newSize, newSize, size - newSize);
            final DelegatingMatrix<E> secondBottomLeft = new DelegatingMatrix<>(second, newSize, 0, size - newSize, newSize);
            final DelegatingMatrix<E> secondBottomRight = new DelegatingMatrix<>(second, newSize, newSize, size - newSize, size - newSize);
            //Partitioning the result matrix
            final Matrix<E> resultTopLeft = new DelegatingMatrix<>(result, 0, 0, newSize, newSize);
            final Matrix<E> resultTopRight = new DelegatingMatrix<>(result, 0, newSize, newSize, size - newSize);
            final Matrix<E> resultBottomLeft = new DelegatingMatrix<>(result, newSize, 0, size - newSize, newSize);
            final Matrix<E> resultBottomRight = new DelegatingMatrix<>(result, newSize, newSize, size - newSize, size - newSize);
            copy(sum(multiply(firstTopLeft, secondTopLeft), multiply(firstTopRight, secondBottomLeft)), resultTopLeft);
            copy(sum(multiply(firstTopLeft, secondTopRight), multiply(firstTopRight, secondBottomRight)), resultTopRight);
            copy(sum(multiply(firstBottomLeft, secondTopLeft), multiply(firstBottomRight, secondBottomLeft)), resultBottomLeft);
            copy(sum(multiply(firstBottomLeft, secondTopRight), multiply(firstBottomRight, secondBottomRight)), resultBottomRight);
        }
        return result;
    }

    private <E extends Number> Matrix<E> sum(Matrix<E> first, Matrix<E> second) {
        final int size = first.getColumns();
        final Matrix<E> result = factory.getMatrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.set(i, j, NumberUtils.add(first.get(i, j), second.get(i, j)));
            }
        }
        return result;
    }

    private <E extends Number> void copy(Matrix<E> from, Matrix<E> to) {
        for (MatrixRow<E> row : from) {
            for (MatrixCell<E> cell : row) {
                to.set(cell.getRowNumber(), cell.getColumnNumber(), cell.getValue());
            }
        }
    }

}
