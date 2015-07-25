package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/20/15)
 */
public class BruteForceLongestCommonSubSequenceFinderTest extends BaseLongestCommonSubSequenceFinderTest {

    @Override
    protected LongestCommonSubSequenceFinder<Integer> getFinder() {
        return new BruteForceLongestCommonSubSequenceFinder<>();
    }

}