package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (08/10/2018)
 */
public class RecursiveEditDistanceCalculatorTest extends BaseEditDistanceCalculatorTest {

    protected EditDistanceCalculator getEditDistanceCalculator() {
        return new RecursiveEditDistanceCalculator();
    }

}
