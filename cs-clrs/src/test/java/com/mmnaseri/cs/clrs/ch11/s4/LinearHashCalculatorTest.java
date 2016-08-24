package com.mmnaseri.cs.clrs.ch11.s4;

import com.mmnaseri.cs.clrs.ch11.HashCalculator;
import com.mmnaseri.cs.clrs.ch11.s3.BaseHashCalculatorTest;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/13/15)
 */
public class LinearHashCalculatorTest extends BaseHashCalculatorTest {

    @Override
    protected HashCalculator[] getHashCalculators() {
        return new HashCalculator[]{new LinearHashCalculator(29, 7, 23)};
    }
}