package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class BaseSlidingWindowMaximumTest {

  protected abstract SlidingWindowMaximumFinder finder();

  @DataProvider
  public Object[][] testDataProvider() {
    return new Object[][] {
      new Object[] {new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[] {3, 3, 5, 5, 6, 7}},
      new Object[] {new int[] {1}, 1, new int[] {1}},
      new Object[] {new int[] {1, -1}, 1, new int[] {1, -1}},
      new Object[] {new int[] {9, 11}, 2, new int[] {11}},
      new Object[] {new int[] {4, -2}, 2, new int[] {4}},
    };
  }

  @Test(dataProvider = "testDataProvider")
  public void testSampleOne(int[] data, int windowSize, int[] expectedMaximums) {
    SlidingWindowMaximumFinder finder = finder();
    assertThat(finder.findMaximums(data, windowSize), is(expectedMaximums));
  }
}
