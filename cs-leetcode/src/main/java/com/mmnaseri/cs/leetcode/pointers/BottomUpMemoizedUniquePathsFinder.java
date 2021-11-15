package com.mmnaseri.cs.leetcode.pointers;

public class BottomUpMemoizedUniquePathsFinder implements UniquePathsFinder {

  @Override
  public int uniquePaths(int m, int n) {
    if (m <= 1 || n <= 1) {
      return 1;
    }
    int[][] memory = new int[m - 1][];
    for (int i = 0; i < m - 1; i++) {
      memory[i] = new int[n - 1];
    }
    for (int i = 0; i < m - 1; i++) {
      for (int j = 0; j < n - 1; j++) {
        int left = i > 0 ? memory[i - 1][j] : 1;
        int up = j > 0 ? memory[i][j - 1] : 1;
        memory[i][j] = left + up;
      }
    }
    return memory[m - 2][n - 2];
  }
}
