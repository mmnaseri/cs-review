package com.mmnaseri.cs.leetcode.pointers;

import org.testng.annotations.Test;

public class OptimizedBottomUpObstacleUniquePathsFinderTest extends BaseObstacleUniquePathsFinderTest {

  @Override
  protected ObstacleUniquePathsFinder getInstance() {
    return new OptimizedBottomUpObstacleUniquePathsFinder();
  }

  @Override
  @Test(enabled = false)
  public void testSolution(int[][] grid, int uniquePaths) {
    super.testSolution(grid, uniquePaths);
  }
}