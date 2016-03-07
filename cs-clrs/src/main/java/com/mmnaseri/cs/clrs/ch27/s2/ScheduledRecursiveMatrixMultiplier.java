package com.mmnaseri.cs.clrs.ch27.s2;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch27.s0.Action;
import com.mmnaseri.cs.clrs.ch27.s0.LoopStep;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.NumberUtils;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.clrs.common.impl.DelegatingMatrix;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 13:35)
 */
@Quality(Stage.TESTED)
public class ScheduledRecursiveMatrixMultiplier implements MatrixMultiplier {

    private final SchedulerFactory schedulerFactory;
    private final MatrixFactory matrixFactory;

    public ScheduledRecursiveMatrixMultiplier(SchedulerFactory schedulerFactory) {
        this(schedulerFactory, new ArrayMatrixFactory());
    }

    public ScheduledRecursiveMatrixMultiplier(SchedulerFactory schedulerFactory, MatrixFactory matrixFactory) {
        this.schedulerFactory = schedulerFactory;
        this.matrixFactory = matrixFactory;
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
        final Matrix<E> result = matrixFactory.getMatrix(first.getColumns(), first.getColumns());
        doMultiply(result, first, second);
        return result;
    }

    private <E extends Number> void doMultiply(final Matrix<E> result, Matrix<E> first, Matrix<E> second) {
        final int size = first.getColumns();
        if (size == 1) {
            result.set(0, 0, NumberUtils.multiply(first.get(0, 0), second.get(0, 0)));
        } else {
            final Matrix<E> temporary = matrixFactory.getMatrix(size, size);
            final int newSize = size / 2;
            //Partitioning the first matrix
            final DelegatingMatrix<E> A11 = new DelegatingMatrix<>(first, 0, 0, newSize, newSize);
            final DelegatingMatrix<E> A12 = new DelegatingMatrix<>(first, 0, newSize, newSize, size - newSize);
            final DelegatingMatrix<E> A21 = new DelegatingMatrix<>(first, newSize, 0, size - newSize, newSize);
            final DelegatingMatrix<E> A22 = new DelegatingMatrix<>(first, newSize, newSize, size - newSize, size - newSize);
            //Partitioning the second matrix
            final DelegatingMatrix<E> B11 = new DelegatingMatrix<>(second, 0, 0, newSize, newSize);
            final DelegatingMatrix<E> B12 = new DelegatingMatrix<>(second, 0, newSize, newSize, size - newSize);
            final DelegatingMatrix<E> B21 = new DelegatingMatrix<>(second, newSize, 0, size - newSize, newSize);
            final DelegatingMatrix<E> B22 = new DelegatingMatrix<>(second, newSize, newSize, size - newSize, size - newSize);
            //Partitioning the result matrix
            final Matrix<E> C11 = new DelegatingMatrix<>(result, 0, 0, newSize, newSize);
            final Matrix<E> C12 = new DelegatingMatrix<>(result, 0, newSize, newSize, size - newSize);
            final Matrix<E> C21 = new DelegatingMatrix<>(result, newSize, 0, size - newSize, newSize);
            final Matrix<E> C22 = new DelegatingMatrix<>(result, newSize, newSize, size - newSize, size - newSize);
            //Partitioning the temporary matrix
            final Matrix<E> T11 = new DelegatingMatrix<>(temporary, 0, 0, newSize, newSize);
            final Matrix<E> T12 = new DelegatingMatrix<>(temporary, 0, newSize, newSize, size - newSize);
            final Matrix<E> T21 = new DelegatingMatrix<>(temporary, newSize, 0, size - newSize, newSize);
            final Matrix<E> T22 = new DelegatingMatrix<>(temporary, newSize, newSize, size - newSize, size - newSize);
            final Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(C11, A11, B11);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(C12, A11, B12);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(C21, A21, B11);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(C22, A21, B12);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(T11, A12, B21);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(T12, A12, B22);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(T21, A22, B21);
                }
            });
            doMultiply(T22, A22, B22);
            scheduler.sync();
            scheduler.loop(0, size, new LoopStep() {
                @Override
                public void perform(final int i) {
                    schedulerFactory.getScheduler().loop(0, size, new LoopStep() {
                        @Override
                        public void perform(final int j) {
                            result.set(i, j, NumberUtils.add(result.get(i, j), temporary.get(i, j)));
                        }
                    });
                }
            });
        }
    }

}
