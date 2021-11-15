package com.mmnaseri.cs.leetcode.pointers;

import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

@Quality(value = Stage.BUGGY, explanation = "Fails on the large data set.")
public class OptimizedBottomUpObstacleUniquePathsFinder implements ObstacleUniquePathsFinder {

  @Override
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int cols = obstacleGrid[0].length;
    int rows = obstacleGrid.length;
    if (obstacleGrid[rows - 1][cols - 1] == 1) {
      return 0;
    }
    int lastRow = 1;
    int lastCol = 1;
    for (int[] row : obstacleGrid) {
      if (row[cols - 1] == 1) {
        lastCol = 0;
        break;
      }
    }
    if (cols == 1) {
      return lastCol;
    }
    for (int i = 0; i < cols; i++) {
      if (obstacleGrid[rows - 1][i] == 1) {
        lastRow = 0;
        break;
      }
    }
    if (rows == 1) {
      return lastRow;
    }
    int[][] paths = new int[rows - 1][];
    for (int i = 0; i < paths.length; i++) {
      paths[i] = new int[cols - 1];
    }
    for (int x = cols - 2; x >= 0; x--) {
      for (int y = rows - 2; y >= 0; y--) {
        if (x == cols - 2 && y == rows - 2) {
          paths[y][x] = Math.max(lastRow, lastCol);
        } else if (x == cols - 2) {
          paths[y][x] = lastCol;
        } else if (y == rows - 2) {
          paths[y][x] = lastRow;
        } else {
          paths[y][x] = paths[y][x + 1] + paths[y + 1][x];
        }
      }
    }
    return paths[0][0];
  }
}
