package com.mmnaseri.cs.clrs.ch27.s1;

import com.mmnaseri.cs.clrs.ch27.s0.SchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.ParallelSchedulerFactory;
import com.mmnaseri.cs.clrs.ch27.s0.impl.SerialSchedulerFactory;
import com.mmnaseri.cs.clrs.common.Matrix;
import com.mmnaseri.cs.clrs.common.Vector;
import com.mmnaseri.cs.clrs.common.impl.ArrayMatrix;
import com.mmnaseri.cs.clrs.common.impl.ArrayVector;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (2/27/16)
 */
public class MatrixVectorMultiplierTest {

    private Vector<Integer> vector;
    private Vector<Integer> expected;
    private Matrix<Integer> matrix;

    @BeforeMethod
    public void setUp() throws Exception {
        matrix = new ArrayMatrix<>(3, 3);
        matrix.set(0, 0, 4);
        matrix.set(0, 1, 6);
        matrix.set(0, 2, 7);
        matrix.set(1, 0, 1);
        matrix.set(1, 1, 1);
        matrix.set(1, 2, 1);
        matrix.set(2, 0, 3);
        matrix.set(2, 1, -1);
        matrix.set(2, 2, -2);
        vector = new ArrayVector<>(3);
        vector.set(0, 2);
        vector.set(1, 3);
        vector.set(2, 1);
        expected = new ArrayVector<>(3);
        expected.set(0, 14);
        expected.set(1, 14);
        expected.set(2, 15);
    }

    @DataProvider
    public Object[][] getSchedulerFactory() {
        return new Object[][]{
            new Object[]{"serial", new SerialSchedulerFactory()},
            new Object[]{"parallel", new ParallelSchedulerFactory()}
        };
    }

    @Test(dataProvider = "getSchedulerFactory")
    public void testSerial(String name, SchedulerFactory factory) throws Exception {
        final MatrixVectorMultiplier multiplier = new MatrixVectorMultiplier(factory);
        final Vector<Integer> result = multiplier.multiply(matrix, vector);
        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(expected.size()));
        for (int i = 0; i < expected.size(); i++) {
            assertThat(result.get(i), is(expected.get(i)));
        }
    }

}