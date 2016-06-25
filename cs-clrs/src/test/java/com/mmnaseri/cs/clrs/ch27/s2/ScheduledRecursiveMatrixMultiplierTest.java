package com.mmnaseri.cs.clrs.ch27.s2;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ContextAwareParallelSchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 13:54)
 */
public class ScheduledRecursiveMatrixMultiplierTest {

    @DataProvider
    public Object[][] schedulerFactoryProvider() {
        return new Object[][]{
            new Object[]{new SerialSchedulerFactory()},
            new Object[]{new ContextAwareParallelSchedulerFactory()}
        };
    }

    @Test(dataProvider = "schedulerFactoryProvider")
    public void testSquareMultiplication(SchedulerFactory schedulerFactory) throws Exception {
        final MatrixMultiplier multiplier = new ScheduledRecursiveMatrixMultiplier(schedulerFactory);
        final Matrix<Integer> first = new ArrayMatrix<>(4, 4);
        first.set(0, 0, 0);
        first.set(0, 1, 1);
        first.set(0, 2, 2);
        first.set(0, 3, 3);
        first.set(1, 0, 1);
        first.set(1, 1, 2);
        first.set(1, 2, 3);
        first.set(1, 3, 0);
        first.set(2, 0, 2);
        first.set(2, 1, 3);
        first.set(2, 2, 0);
        first.set(2, 3, 1);
        first.set(3, 0, 3);
        first.set(3, 1, 0);
        first.set(3, 2, 1);
        first.set(3, 3, 2);
        final Matrix<Integer> second = new ArrayMatrix<>(4, 4);
        second.set(0, 0, 0);
        second.set(0, 1, 1);
        second.set(0, 2, -2);
        second.set(0, 3, -4);
        second.set(1, 0, 1);
        second.set(1, 1, -1);
        second.set(1, 2, -3);
        second.set(1, 3, 0);
        second.set(2, 0, 3);
        second.set(2, 1, -3);
        second.set(2, 2, 0);
        second.set(2, 3, 1);
        second.set(3, 0, -7);
        second.set(3, 1, -1);
        second.set(3, 2, 1);
        second.set(3, 3, -1);
        final Matrix<Integer> result = multiplier.multiply(first, second);
        assertThat(result.getRows(), is(4));
        assertThat(result.getColumns(), is(4));
        assertThat(result.get(0, 0), is(-14));
        assertThat(result.get(0, 1), is(-10));
        assertThat(result.get(0, 2), is(0));
        assertThat(result.get(0, 3), is(-1));
        assertThat(result.get(1, 0), is(11));
        assertThat(result.get(1, 1), is(-10));
        assertThat(result.get(1, 2), is(-8));
        assertThat(result.get(1, 3), is(-1));
        assertThat(result.get(2, 0), is(-4));
        assertThat(result.get(2, 1), is(-2));
        assertThat(result.get(2, 2), is(-12));
        assertThat(result.get(2, 3), is(-9));
        assertThat(result.get(3, 0), is(-11));
        assertThat(result.get(3, 1), is(-2));
        assertThat(result.get(3, 2), is(-4));
        assertThat(result.get(3, 3), is(-13));
    }

}