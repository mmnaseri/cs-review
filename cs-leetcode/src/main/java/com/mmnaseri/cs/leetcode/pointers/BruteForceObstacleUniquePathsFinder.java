package com.mmnaseri.cs.leetcode.pointers;

public class BruteForceObstacleUniquePathsFinder implements ObstacleUniquePathsFinder {

  @Override
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    return uniquePathsWithObstacles(obstacleGrid, 0, 0);
  }

  private int uniquePathsWithObstacles(int[][] obstacleGrid, int x, int y) {
    if (obstacleGrid[y][x] == 1) {
      return 0;
    }
    if (y == obstacleGrid.length - 1 && x == obstacleGrid[y].length - 1) {
      return 1;
    }
    if (y == obstacleGrid.length - 1) {
      return uniquePathsWithObstacles(obstacleGrid, x + 1, y);
    }
    if (x == obstacleGrid[0].length - 1) {
      return uniquePathsWithObstacles(obstacleGrid, x, y + 1);
    }
    return uniquePathsWithObstacles(obstacleGrid, x + 1, y)
        + uniquePathsWithObstacles(obstacleGrid, x, y + 1);
  }
}
