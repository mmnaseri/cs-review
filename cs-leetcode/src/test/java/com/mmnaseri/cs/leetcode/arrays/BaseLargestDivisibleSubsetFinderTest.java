package com.mmnaseri.cs.leetcode.arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

public abstract class BaseLargestDivisibleSubsetFinderTest {

  protected abstract LargestDivisibleSubsetFinder getInstance();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {new int[] {1, 2, 3}, Set.of(Set.of(1, 2), Set.of(1, 3))},
      new Object[] {new int[] {1, 2, 4, 8}, Set.of(Set.of(1, 2, 4, 8))},
      new Object[] {new int[] {3, 5, 7, 11}, Set.of(Set.of(3), Set.of(5), Set.of(7), Set.of(11))},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(int[] numbers, Set<Set<Integer>> validSolutions) {
    assertThat(new HashSet<>(getInstance().largestDivisibleSubset(numbers)), isIn(validSolutions));
  }
}
