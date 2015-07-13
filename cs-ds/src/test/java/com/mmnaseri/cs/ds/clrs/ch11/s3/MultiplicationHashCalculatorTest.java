package com.mmnaseri.cs.ds.clrs.ch11.s3;

import com.mmnaseri.cs.ds.clrs.ch11.HashCalculator;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/13/15)
 */
public class MultiplicationHashCalculatorTest extends BaseHashCalculatorTest {

    public static final double PHI = 0.6180339887;

    @Override
    protected HashCalculator getHashCalculator() {
        return new MultiplicationHashCalculator(PHI);
    }

}