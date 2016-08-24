package com.mmnaseri.cs.clrs.ch27.s1;

import com.mmnaseri.cs.clrs.ch27.s0.LoopStep;
import com.mmnaseri.cs.clrs.ch27.s0.Scheduler;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.Vector;
import com.mmnaseri.cs.clrs.common.impl.ArrayVector;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (2/27/16)
 */
@Quality(Stage.TESTED)
public class MatrixVectorMultiplier {

    private final Scheduler scheduler;

    public MatrixVectorMultiplier(SchedulerFactory factory) {
        this.scheduler = factory.getScheduler();
    }

    public Vector<Integer> multiply(final Matrix<Integer> matrix, final Vector<Integer> vector) {
        final Vector<Integer> result = new ArrayVector<>(vector.size());
        scheduler.loop(0, result.size(), new LoopStep() {
            @Override
            public void perform(int i) {
                result.set(i, 0);
            }
        });
        scheduler.loop(0, result.size(), new LoopStep() {
            @Override
            public void perform(int i) {
                for (int j = 0; j < result.size(); j++) {
                    result.set(i, result.get(i) + matrix.get(j, i) * vector.get(j));
                }
            }
        });
        return result;
    }

}
