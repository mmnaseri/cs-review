package com.mmnaseri.cs.leetcode.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class MiniParser {

  public NestedInteger deserialize(String s) {
    Stack<NestedInteger> context = new Stack<>();
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == '-' || Character.isDigit(s.charAt(i))) {
        // expect we aren't reading a number.
        StringBuilder value = new StringBuilder();
        if (s.charAt(i) == '-') {
          value.append("-");
          i++;
        }
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
          value.append(s.charAt(i++));
        }
        int parsed = Integer.parseInt(value.toString());
        if (context.isEmpty()) {
          context.push(new NestedInteger(parsed));
        } else {
          NestedInteger current = context.peek();
          if (current.isInteger()) {
            current.add(new NestedInteger(current.getInteger()));
          }
          current.add(new NestedInteger(parsed));
        }
      } else if (s.charAt(i) == '[') {
        NestedInteger value = new NestedInteger();
        if (!context.isEmpty()) {
          NestedInteger current = context.peek();
          if (current.isInteger()) {
            current.add(new NestedInteger(current.getInteger()));
          }
          current.add(value);
        }
        context.push(value);
        i++;
      } else if (s.charAt(i) == ']') {
        NestedInteger popped = context.pop();
        if (context.isEmpty()) {
          context.push(popped);
          break;
        }
        i++;
      } else {
        i++;
      }
    }
    return context.pop();
  }

  public static class NestedInteger {

    public static NestedInteger of(int value) {
      return new NestedInteger(value);
    }

    public static NestedInteger of(NestedInteger... value) {
      return new NestedInteger(Arrays.asList(value));
    }

    private Integer value;
    private List<NestedInteger> list;

    public NestedInteger() {}

    public NestedInteger(Integer value) {
      this.value = value;
    }

    public NestedInteger(List<NestedInteger> list) {
      this.list = list;
    }

    public boolean isInteger() {
      return value != null;
    }

    public void add(NestedInteger value) {
      if (list == null) {
        list = new ArrayList<>();
      }
      list.add(value);
      this.value = null;
    }

    public Integer getInteger() {
      return value;
    }

    public void setInteger(int value) {
      this.value = value;
      if (this.list != null) {
        this.list.clear();
      }
      this.list = null;
    }

    public List<NestedInteger> getList() {
      return list;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      NestedInteger that = (NestedInteger) o;
      return Objects.equals(value, that.value) && Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, list);
    }

    @Override
    public String toString() {
      return value != null ? value.toString() : (list != null ? list.toString() : "[]");
    }
  }
}
