package com.mmnaseri.cs.clrs.ch11.s3;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public class DivisionHashCalculatorTest extends BaseHashCalculatorTest {

    @Override
    protected HashCalculator[] getHashCalculators() {
        return new HashCalculator[]{new DivisionHashCalculator()};
    }

    @Override
    protected int[] getCapacities() {
        return new int[]{701};
    }

}