package com.mmnaseri.cs.leetcode.pointers;

public class BottomUpObstacleUniquePathsFinder implements ObstacleUniquePathsFinder {

  @Override
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int cols = obstacleGrid[0].length;
    int rows = obstacleGrid.length;
    if (obstacleGrid[rows - 1][cols - 1] == 1) {
      return 0;
    }
    int[][] paths = new int[rows][];
    for (int i = 0; i < paths.length; i++) {
      paths[i] = new int[cols];
    }
    for (int x = cols - 1; x >= 0; x --) {
      for (int y = rows - 1; y >= 0; y --) {
        if (obstacleGrid[y][x] == 1) {
          paths[y][x] = 0;
        } else if (x == cols - 1 && y == rows - 1) {
          paths[y][x] = 1;
        } else if (x == cols - 1) {
          paths[y][x] = paths[y + 1][x];
        } else if (y == rows - 1) {
          paths[y][x] = paths[y][x + 1];
        } else {
          paths[y][x] = paths[y][x + 1] + paths[y + 1][x];
        }
      }
    }
    return paths[0][0];
  }

}
