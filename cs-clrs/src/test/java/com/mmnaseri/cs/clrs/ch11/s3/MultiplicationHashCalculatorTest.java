package com.mmnaseri.cs.clrs.ch11.s3;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public class MultiplicationHashCalculatorTest extends BaseHashCalculatorTest {

    public static final double PHI = 0.6180339887;

    @Override
    protected HashCalculator[] getHashCalculators() {
        return new HashCalculator[]{
            new MultiplicationHashCalculator(PHI),
            new MultiplicationHashCalculator(Math.PI),
            new MultiplicationHashCalculator(Math.E),
        };
    }

}