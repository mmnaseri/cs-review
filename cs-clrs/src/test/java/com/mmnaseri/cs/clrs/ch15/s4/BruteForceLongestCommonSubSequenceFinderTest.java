package com.mmnaseri.cs.clrs.ch15.s4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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