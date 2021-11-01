package com.mmnaseri.cs.leetcode.puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlusSignFinder {

  public long getPlusSignCount(int[] L, String D) {
    List<Line> lines = new ArrayList<>();
    int x = 0;
    int y = 0;
    for (int i = 0; i < D.length(); i++) {
      int nextX = x;
      int nextY = y;
      switch (D.charAt(i)) {
        case 'R':
          nextX = x + L[i];
          break;
        case 'L':
          nextX = x - L[i];
          break;
        case 'U':
          nextY = y + L[i];
          break;
        case 'D':
          nextY = y - L[i];
          break;
      }
      lines.add(new Line(x, y, nextX, nextY));
      x = nextX;
      y = nextY;
    }
    List<Line> drawnLines = new ArrayList<>(lines);
    lines.clear();
    while (!drawnLines.isEmpty()) {
      Line line = drawnLines.remove(0);
      Set<Integer> removed = new HashSet<>();
      for (int i = 0; i < drawnLines.size(); i++) {
        Line other = drawnLines.get(i);
        if (line.conjoins(other)) {
          line = line.merge(other);
          removed.add(i);
        }
      }
      removed.stream()
          .sorted(Comparator.<Integer>naturalOrder().reversed())
          .mapToInt(Integer::intValue)
          .forEach(drawnLines::remove);
      lines.add(line);
    }
    return lines.stream()
        .filter(Line::isHorizontal)
        .mapToLong(line -> lines.stream().filter(Line::isVertical).filter(line::intersects).count())
        .sum();
  }

  public static class Line {
    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;

    public Line(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
    }

    public int startX() {
      return Math.min(x1, x2);
    }

    public int endX() {
      return Math.max(x1, x2);
    }

    public int startY() {
      return Math.min(y1, y2);
    }

    public int endY() {
      return Math.max(y1, y2);
    }

    public boolean isHorizontal() {
      return y1 == y2;
    }

    public boolean isVertical() {
      return x1 == x2;
    }

    public boolean intersects(Line other) {
      if (isHorizontal()) {
        return other.isVertical()
            && Math.min(other.y1, other.y2) < y1
            && Math.max(other.y1, other.y2) > y1
            && Math.min(x1, x2) < other.x1
            && Math.max(x1, x2) > other.x2;
      } else {
        return other.intersects(this);
      }
    }

    public boolean conjoins(Line other) {
      if (isHorizontal() && other.isHorizontal()) {
        return y1 == other.y1 && (endX() >= other.startX() || startX() <= other.endX());
      } else if (isVertical() && other.isVertical()) {
        return x1 == other.x1 && (endY() >= other.startY() || startY() <= other.endY());
      }
      return false;
    }

    public Line merge(Line other) {
      if (isHorizontal()) {
        return new Line(
          Math.min(Math.min(x1, x2), Math.min(other.x1, other.x2)),
          y1, Math.max(Math.max(x1, x2), Math.max(other.x1, other.x2)),
          y2);
      } else {
        return new Line(
          x1,
          Math.min(Math.min(y1, y2), Math.min(other.y1, other.y2)), x2,
          Math.max(Math.max(y1, y2), Math.max(other.y1, other.y2)));
      }
    }

    @Override
    public String toString() {
      return String.format("(%d, %d) -> (%d, %d)", x1, y1, x2, y2);
    }
  }
}
