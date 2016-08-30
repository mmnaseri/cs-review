package com.mmnaseri.cs.clrs.ch28.custom.impl;

import com.mmnaseri.cs.clrs.ch28.custom.InvertibleMatrixGenerator;

/**
 * @author Milad Naseri (milad.naseri@cdk.com)
 * @since 1.0 (8/30/16, 1:55 PM)
 */
public class SimpleInvertibleMatrixGeneratorTest extends BaseInvertibleMatrixGeneratorTest {

    @Override
    protected InvertibleMatrixGenerator<Double> getGenerator() {
        return new SimpleInvertibleMatrixGenerator<>(Double.class);
    }

}
