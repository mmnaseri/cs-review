package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FirstMissingPositiveTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {new int[] {1, 2, 0}, 3},
      new Object[] {new int[] {3, 4, -1, 1}, 2},
      new Object[] {new int[] {7, 8, 9, 11, 12}, 1},
      new Object[] {new int[] {1}, 2},
    };
  }

  @Test(dataProvider = "dataProvider", enabled = false)
  public void testSolution(int[] numbers, int expected) {
    int actual = new FirstMissingPositive().firstMissingPositive(numbers);
    assertThat(actual, is(expected));
  }
}
