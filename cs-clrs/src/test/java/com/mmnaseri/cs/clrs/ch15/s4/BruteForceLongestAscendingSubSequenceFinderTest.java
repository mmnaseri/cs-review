package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
public class BruteForceLongestAscendingSubSequenceFinderTest extends BaseLongestAscendingSubSequenceFinderTest {

    @Override
    protected LongestAscendingSubSequenceFinder<Integer> getFinder() {
        return new BruteForceLongestAscendingSubSequenceFinder<>(NATURAL_ORDER);
    }

}