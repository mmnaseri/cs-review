package com.mmnaseri.cs.clrs.ch27.s2;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch04.s2.SimpleMatrixMultiplier;
import com.mmnaseri.cs.clrs.ch27.s0.LoopStep;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.MatrixFactory;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrixFactory;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 08:55)
 */
@Quality(Stage.TESTED)
public class ScheduledSimpleMatrixMultiplier implements MatrixMultiplier {

    private final MatrixFactory factory;
    private SchedulerFactory schedulerFactory;

    public ScheduledSimpleMatrixMultiplier(SchedulerFactory schedulerFactory) {
        this(schedulerFactory, new ArrayMatrixFactory());
    }

    public ScheduledSimpleMatrixMultiplier(SchedulerFactory schedulerFactory, MatrixFactory factory) {
        this.schedulerFactory = schedulerFactory;
        this.factory = factory;
    }

    @Override
    public <E extends Number> Matrix<E> multiply(final Matrix<E> first, final Matrix<E> second) {
        if (first.getRows() != second.getColumns()) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        final Matrix<E> result = factory.getMatrix(first.getRows(), second.getColumns());
        schedulerFactory.getScheduler().loop(0, first.getRows(), new LoopStep() {
            @Override
            public void perform(final int i) {
                schedulerFactory.getScheduler().loop(0, second.getColumns(), new LoopStep() {
                    @Override
                    public void perform(final int j) {
                        SimpleMatrixMultiplier.multiplyRowByColumn(first, second, result, i, j);
                    }
                });
            }
        });
        return result;
    }

}
