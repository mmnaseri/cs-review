package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class BaseMinimumWindowSubstringFinderTest {

  protected abstract MinimumWindowSubstringFinder getFinder();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {"ADOBECODEBANC", "ABC", "BANC"},
      new Object[] {"a", "a", "a"},
      new Object[] {"a", "aa", ""},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String haystack, String needle, String expected) {
    String actual = getFinder().minWindow(haystack, needle);
    assertThat(actual, is(expected));
  }
}
