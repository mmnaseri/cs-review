package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LongestConsecutiveSubSequenceFinderTest {

  @Test
  public void testSolution() {
    assertThat(
        new LongestConsecutiveSubSequenceFinder()
            .longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}),
        is(4));
  }
}
