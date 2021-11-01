package com.mmnaseri.cs.leetcode.puzzle;

import com.mmnaseri.cs.leetcode.puzzle.PlusSignFinder.Line;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PlusSignFinderTest {

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][] {
      new Object[] {new int[] {6, 3, 4, 5, 1, 6, 3, 3, 4}, "ULDRULURD", 4L},
      new Object[] {new int[] {1, 1, 1, 1, 1, 1, 1, 1}, "RDLUULDR", 1L},
      new Object[] {new int[] {1, 2, 2, 1, 1, 2, 2, 1}, "UDUDLRLR", 1L},
    };
  }

  @Test(dataProvider = "dataProvider")
  public void testSolution(int[] lengths, String directions, long expected) {
    PlusSignFinder finder = new PlusSignFinder();
    long count = finder.getPlusSignCount(lengths, directions);
    assertThat(count, is(expected));
  }

  @Test
  public void testConjoins() {
    assertThat(new Line(0, 0, 0, 1).conjoins(new Line(0, 1, 0, -1)), is(true));
  }
}
