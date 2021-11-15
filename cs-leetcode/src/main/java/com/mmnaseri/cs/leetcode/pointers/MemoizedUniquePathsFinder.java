package com.mmnaseri.cs.leetcode.pointers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MemoizedUniquePathsFinder implements UniquePathsFinder {

  @Override
  public int uniquePaths(int m, int n) {
    return uniquePaths(new Point(m, n), new HashMap<>());
  }

  private static int uniquePaths(Point point, Map<Point, Integer> memory) {
    if (point.x == 1 || point.y == 1) {
      return 1;
    }
    if (memory.containsKey(point)) {
      return memory.get(point);
    }
    int answer = uniquePaths(point.left(), memory) + uniquePaths(point.up(), memory);
    memory.put(point, answer);
    return answer;
  }

  private static class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    private Point left() {
      return new Point(x - 1, y);
    }

    private Point up() {
      return new Point(x, y - 1);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
