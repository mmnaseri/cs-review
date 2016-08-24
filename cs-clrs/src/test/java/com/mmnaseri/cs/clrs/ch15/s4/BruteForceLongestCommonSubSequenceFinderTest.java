package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/20/15)
 */
public class BruteForceLongestCommonSubSequenceFinderTest extends BaseLongestCommonSubSequenceFinderTest {

    @Override
    protected LongestCommonSubSequenceFinder<Integer> getFinder() {
        return new BruteForceLongestCommonSubSequenceFinder<>();
    }

}