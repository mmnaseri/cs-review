package com.mmnaseri.cs.clrs.ch15.sp;

/**
 * @author Ramin Farhanian (rf.tech@icloud.com)
 * @since 1.0 (09/10/2018)
 */
public class BottomUpEditDistanceCalculatorTest extends BaseEditDistanceCalculatorTest {

    protected EditDistanceCalculator getEditDistanceCalculator() {
        return new BottomUpEditDistanceCalculator();
    }

}
