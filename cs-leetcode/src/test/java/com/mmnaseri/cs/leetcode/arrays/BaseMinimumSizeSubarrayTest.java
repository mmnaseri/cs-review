package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class BaseMinimumSizeSubarrayTest {

  protected abstract MinimumSizeSubarrayFinder getFinder();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {7, new int[] {2, 1, 4, 3, 2, 5}, 2},
      new Object[] {7, new int[] {2, 3, 1, 2, 4, 3}, 2},
      new Object[] {8, new int[] {3, 4, 1, 1, 6}, 3},
      new Object[] {15, new int[] {1, 3, 2, 1, 5}, 0},
      new Object[] {4, new int[] {1, 4, 4}, 1},
      new Object[] {9, new int[] {3, 4, 1, 1, 2, 1}, 4},
      new Object[] {
        11,
        new int[] {
          1, 1, 1, 1, 1, 1, 1, 1,
        },
        0
      },
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testMinimumSubArray(int target, int[] nums, int expectedLength) {
    int length = getFinder().minSubArrayLen(target, nums);
    assertThat(length, is(expectedLength));
  }
}
