package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/22/15)
 */
public class BottomUpLongestAscendingSubSequenceFinderTest extends BaseLongestAscendingSubSequenceFinderTest {

    @Override
    protected LongestAscendingSubSequenceFinder<Integer> getFinder() {
        return new BottomUpLongestAscendingSubSequenceFinder<>(NATURAL_ORDER);
    }
}