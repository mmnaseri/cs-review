package com.mmnaseri.cs.skiena.ch04.s9.ss2;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (11/12/16, 4:55 PM)
 */
public class BinarySearchPrefixLengthFinderTest extends BasePrefixLengthFinderTest {

    @Override
    protected PrefixLengthFinder<Integer> getLengthFinder() {
        return new BinarySearchPrefixLengthFinder<>();
    }

}