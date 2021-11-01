package com.mmnaseri.cs.leetcode.strings;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class BaseLongestSubstringWithDistinctCharsTest {

  protected abstract LongestSubstringWithDistinctChars getSolver();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][]{
      new Object[]{"aabacbebebe", 3, 7},
      new Object[]{"aaaa", 2, -1},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(String str, int distinctChars, int expectedAnswer) {
    int answer = getSolver().find(str, distinctChars);
    assertThat(answer, is(expectedAnswer));
  }
}