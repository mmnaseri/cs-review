package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LargestUniqueSubstringTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {"", 0},
      new Object[] {"a", 1},
      new Object[] {"aaaaaa", 1},
      new Object[] {"dvdf", 3},
      new Object[] {"au", 2},
      new Object[] {"abba", 2},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String str, int expected) {
    assertThat(new LargestUniqueSubstring().lengthOfLongestSubstring(str), is(expected));
  }
}
