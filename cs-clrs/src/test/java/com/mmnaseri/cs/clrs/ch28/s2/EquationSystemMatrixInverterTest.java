package com.mmnaseri.cs.clrs.ch28.s2;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/29/16, 12:52 PM)
 */
public class EquationSystemMatrixInverterTest extends BaseMatrixInverterTest {

    @Override
    protected MatrixInverter<Double> getInverter() {
        return new EquationSystemMatrixInverter<>(Double.class);
    }

}
