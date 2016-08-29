package com.mmnaseri.cs.clrs.ch28.s2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/28/16, 10:36 PM)
 */
public abstract class BaseMatrixInverterTest {

    private MatrixInverter<Double> inverter;

    protected abstract MatrixInverter<Double> getInverter();

    @BeforeMethod
    public void setUp() throws Exception {
        inverter = getInverter();
    }

    @Test
    public void testInversion() throws Exception {
    }

}