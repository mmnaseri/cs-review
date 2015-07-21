package com.mmnaseri.cs.clrs.ch15.s4;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (7/21/15)
 */
public class MemoizedLongestCommonSubSequenceFinderTest extends BaseLongestCommonSubSequenceFinderTest {

    @Override
    protected LongestCommonSubSequenceFinder<Integer> getFinder() {
        return new MemoizedLongestCommonSubSequenceFinder<>();
    }
}