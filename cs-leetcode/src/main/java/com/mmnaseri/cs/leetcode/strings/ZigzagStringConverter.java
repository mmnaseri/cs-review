package com.mmnaseri.cs.leetcode.strings;

public class ZigzagStringConverter {

  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }
    StringBuilder builder = new StringBuilder();
    // top: 2k(R-1) [0, 4, 8, 12, ...]
    // middle: (2k(R-1)+r, 2(k+1)(R-1)-r)
    // bottom: (2k+1)(R-1) [2, 6, 10, 14, ...]
    // k in [0..|s| / R]
    int cols = (int) Math.ceil(1. * s.length() / numRows);
    // top row.
    for (int k = 0; k < cols; k++) {
      int index = 2 * k * (numRows - 1);
      if (index < s.length()) {
        builder.append(s.charAt(index));
      }
    }
    // middle rows.
    for (int row = 1; row < numRows - 1; row++) {
      for (int k = 0; k < cols; k++) {

        int first = 2 * k * (numRows - 1) + row;
        int second = 2 * (k + 1) * (numRows - 1) - row;
        if (first < s.length()) {
          builder.append(s.charAt(first));
        }
        if (second < s.length()) {
          builder.append(s.charAt(second));
        }
      }
    }
    if (numRows > 1) {
      // bottom row.
      for (int k = 0; k < cols; k++) {
        int index = (2 * k + 1) * (numRows - 1);
        if (index < s.length()) {
          builder.append(s.charAt(index));
        }
      }
    }
    return builder.toString();
  }
}
