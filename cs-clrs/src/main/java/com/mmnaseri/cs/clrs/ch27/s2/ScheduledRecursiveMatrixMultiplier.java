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
            //Partitioning the temporary matrix
            final Matrix<E> temporaryTopLeft = new DelegatingMatrix<>(temporary, 0, 0, newSize, newSize);
            final Matrix<E> temporaryTopRight = new DelegatingMatrix<>(temporary, 0, newSize, newSize, size - newSize);
            final Matrix<E> temporaryBottomLeft = new DelegatingMatrix<>(temporary, newSize, 0, size - newSize, newSize);
            final Matrix<E> temporaryBottomRight = new DelegatingMatrix<>(temporary, newSize, newSize, size - newSize, size - newSize);
            final Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(resultTopLeft, firstTopLeft, secondTopLeft);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(resultTopRight, firstTopLeft, secondTopRight);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(resultBottomLeft, firstBottomLeft, secondTopLeft);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(resultBottomRight, firstBottomLeft, secondTopRight);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(temporaryTopLeft, firstTopRight, secondBottomLeft);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(temporaryTopRight, firstTopRight, secondBottomRight);
                }
            });
            scheduler.spawn(new Action() {
                @Override
                public void perform() {
                    doMultiply(temporaryBottomLeft, firstBottomRight, secondBottomLeft);
                }
            });
            doMultiply(temporaryBottomRight, firstBottomRight, secondBottomRight);
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
