package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/21/15)
 */
public class BruteForceLongestAscendingSubSequenceFinderTest extends BaseLongestAscendingSubSequenceFinderTest {

    @Override
    protected LongestAscendingSubSequenceFinder<Integer> getFinder() {
        return new BruteForceLongestAscendingSubSequenceFinder<>(NATURAL_ORDER);
    }

}