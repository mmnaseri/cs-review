package com.mmnaseri.cs.clrs.ch27.s2;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch04.s2.SimpleMatrixMultiplier;
import com.mmnaseri.cs.clrs.ch27.s0.LoopStep;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
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
    private final Scheduler scheduler;

    public ScheduledSimpleMatrixMultiplier(SchedulerFactory schedulerFactory) {
        this(schedulerFactory, new ArrayMatrixFactory());
    }

    public ScheduledSimpleMatrixMultiplier(SchedulerFactory schedulerFactory, MatrixFactory factory) {
        this.scheduler = schedulerFactory.getScheduler();
        this.factory = factory;
    }

    @Override
    public <E extends Number> Matrix<E> multiply(final Matrix<E> first, final Matrix<E> second) {
        if (first.getRows() != second.getColumns()) {
            throw new IllegalArgumentException("Incompatible matrix dimensions");
        }
        final Matrix<E> result = factory.getMatrix(second.getRows(), first.getColumns());
        scheduler.loop(0, first.getColumns(), new LoopStep() {
            @Override
            public void perform(final int j) {
                scheduler.loop(0, second.getRows(), new LoopStep() {
                    @Override
                    public void perform(final int i) {
                        SimpleMatrixMultiplier.multiplyRowByColumn(first, second, result, i, j);
                    }
                });
            }
        });
        return result;
    }

}
