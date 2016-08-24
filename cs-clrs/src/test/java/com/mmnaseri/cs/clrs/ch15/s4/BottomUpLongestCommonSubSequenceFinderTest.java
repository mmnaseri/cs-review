package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (7/21/15)
 */
public class BottomUpLongestCommonSubSequenceFinderTest extends BaseLongestCommonSubSequenceFinderTest {

    @Override
    protected LongestCommonSubSequenceFinder<Integer> getFinder() {
        return new BottomUpLongestCommonSubSequenceFinder<>();
    }

}