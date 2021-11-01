package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DuplicateNumberFinderTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {new int[] {1, 3, 4, 2, 2}, 2},
      new Object[] {new int[] {3, 1, 3, 4, 2}, 3},
      new Object[] {new int[] {1, 1}, 1},
      new Object[] {new int[] {1, 1, 2}, 1},
      new Object[] {new int[] {2, 2, 2, 2, 2}, 2},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(int[] nums, int expected) {
    DuplicateNumberFinder finder = new DuplicateNumberFinder();
    int actual = finder.findDuplicate(nums);
    assertThat(actual, is(expected));
  }
}
