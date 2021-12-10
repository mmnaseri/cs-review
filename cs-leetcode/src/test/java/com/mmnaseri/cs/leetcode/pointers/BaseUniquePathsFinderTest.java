package com.mmnaseri.cs.leetcode.pointers;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class BaseUniquePathsFinderTest {

  protected abstract UniquePathsFinder getInstance();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {3, 7, 28},
      new Object[] {3, 2, 3},
      new Object[] {7, 3, 28},
      new Object[] {3, 3, 6},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(int rows, int cols, int uniquePaths) {
    assertThat(getInstance().uniquePaths(rows, cols), is(uniquePaths));
  }
}
