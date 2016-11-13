package com.mmnaseri.cs.skiena.ch04.s9.ss2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 1:14 PM)
 */
public class NaivePrefixLengthFinderTest extends BasePrefixLengthFinderTest {

    @Override
    protected NaivePrefixLengthFinder<Integer> getLengthFinder() {
        return new NaivePrefixLengthFinder<>();
    }

}