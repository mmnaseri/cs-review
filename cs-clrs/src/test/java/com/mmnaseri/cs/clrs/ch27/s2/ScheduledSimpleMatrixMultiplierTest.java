package com.mmnaseri.cs.clrs.ch27.s2;

import com.mmnaseri.cs.clrs.ch04.s2.MatrixMultiplier;
import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ParallelSchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (2016/03/07, 09:01)
 */
public class ScheduledSimpleMatrixMultiplierTest {

    @DataProvider
    public Object[][] schedulerFactoryProvider() {
        return new Object[][]{
            new Object[]{new ParallelSchedulerFactory()},
            new Object[]{new SerialSchedulerFactory()}
        };
    }

    /**
     *
     * |  2  3  |
     * |  0  4  |  * |  6  1  1  | =  |  11   27 |
     * | -1  5  |    |  0  3  2  |    |  -2   22 |
     *
     */
    @Test(dataProvider = "schedulerFactoryProvider")
    public void testMultiplication(SchedulerFactory schedulerFactory) throws Exception {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 2);
        first.set(0, 0, 2);
        first.set(0, 1, 3);
        first.set(1, 0, 0);
        first.set(1, 1, 4);
        first.set(2, 0, -1);
        first.set(2, 1, 5);
        final Matrix<Integer> second = new ArrayMatrix<>(2, 3);
        second.set(0, 0, 6);
        second.set(0, 1, 1);
        second.set(0, 2, 1);
        second.set(1, 0, 0);
        second.set(1, 1, 3);
        second.set(1, 2, 2);
        final MatrixMultiplier multiplier = new ScheduledSimpleMatrixMultiplier(schedulerFactory);
        final Matrix<Integer> multiplication = multiplier.multiply(first, second);
        assertThat(multiplication.getColumns(), is(3));
        assertThat(multiplication.getRows(), is(3));
        assertThat(multiplication.get(0, 0), is(12));
        assertThat(multiplication.get(0, 1), is(11));
        assertThat(multiplication.get(0, 2), is(8));
        assertThat(multiplication.get(1, 0), is(0));
        assertThat(multiplication.get(1, 1), is(12));
        assertThat(multiplication.get(1, 2), is(8));
        assertThat(multiplication.get(2, 0), is(-6));
        assertThat(multiplication.get(2, 1), is(14));
        assertThat(multiplication.get(2, 2), is(9));
    }

    @Test
    public void testIntegrity() {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 2);
        first.set(0, 0, 2);
        first.set(0, 1, 3);
        first.set(1, 0, 0);
        first.set(1, 1, 4);
        first.set(2, 0, -1);
        first.set(2, 1, 5);
        final Matrix<Integer> second = new ArrayMatrix<>(2, 3);
        second.set(0, 0, 6);
        second.set(0, 1, 1);
        second.set(0, 2, 1);
        second.set(1, 0, 0);
        second.set(1, 1, 3);
        second.set(1, 2, 2);
        final Matrix<Integer> firstMultiplication = new ScheduledSimpleMatrixMultiplier(new SerialSchedulerFactory()).multiply(first, second);
        final Matrix<Integer> secondMultiplication = new ScheduledSimpleMatrixMultiplier(new ParallelSchedulerFactory()).multiply(first, second);
        for (int i = 0; i < firstMultiplication.getRows(); i++) {
            for (int j = 0; j < firstMultiplication.getColumns(); j++) {
                assertThat(firstMultiplication.get(i, j), is(secondMultiplication.get(i, j)));
            }
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incompatible matrix dimensions")
    public void testIncompatibleMatrices() throws Exception {
        final Matrix<Integer> first = new ArrayMatrix<>(3, 3);
        final Matrix<Integer> second = new ArrayMatrix<>(4, 4);
        final MatrixMultiplier multiplier = new ScheduledSimpleMatrixMultiplier(new SerialSchedulerFactory());
        multiplier.multiply(first, second);
    }

}