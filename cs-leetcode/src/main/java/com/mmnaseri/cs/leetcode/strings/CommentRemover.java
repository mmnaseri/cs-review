package com.mmnaseri.cs.leetcode.strings;

import java.util.ArrayList;
import java.util.List;

public class CommentRemover {
  public List<String> removeComments(String[] source) {
    List<String> output = new ArrayList<>();
    boolean multiline = false;
    boolean eatNewLine = false;
    for (String line : source) {
      StringBuilder builder = new StringBuilder();
      boolean ignored = multiline;
      for (int i = 0; i < line.length(); i++) {
        if (!ignored
            && i < line.length() - 1
            && line.charAt(i) == '/'
            && line.charAt(i + 1) == '/') {
          break;
        }
        if (!ignored
            && i < line.length() - 1
            && line.charAt(i) == '/'
            && line.charAt(i + 1) == '*') {
          i++;
          ignored = true;
          continue;
        }
        if (ignored
            && i < line.length() - 1
            && line.charAt(i) == '*'
            && line.charAt(i + 1) == '/') {
          i++;
          ignored = false;
          continue;
        }
        if (!ignored) {
          builder.append(line.charAt(i));
        }
      }
      if (ignored) {
        multiline = true;
        eatNewLine = eatNewLine || builder.length() != 0;
      } else {
        if (multiline && builder.length() == 0) {
          eatNewLine = false;
        }
        multiline = false;
      }
      if (builder.length() != 0) {
        if (eatNewLine && !multiline && !output.isEmpty()) {
          output.set(output.size() - 1, output.get(output.size() - 1) + builder);
          eatNewLine = false;
        } else {
          output.add(builder.toString());
        }
      }
    }
    return output;
  }
}
