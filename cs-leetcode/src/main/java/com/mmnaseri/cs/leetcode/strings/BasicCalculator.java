package com.mmnaseri.cs.leetcode.strings;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <a
 * href="https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/836/">Problem.</a>
 */
public class BasicCalculator {

  public int eval(String expression) {
    Stack<Integer> operands = new Stack<>();
    Stack<Character> operators = new Stack<>();
    AtomicInteger cursor = new AtomicInteger();
    while (true) {
      if (cursor.get() >= expression.length()) {
        while (!operators.isEmpty()) {
          Integer right = operands.pop();
          Integer left = operands.pop();
          operands.push(apply(operators.pop(), left, right));
        }
        break;
      }
      if (Character.isWhitespace(expression.charAt(cursor.get()))) {
        skipWhitespace(expression, cursor);
      } else if (Character.isDigit(expression.charAt(cursor.get()))) {
        operands.push(readOperand(expression, cursor));
      } else {
        char operator = expression.charAt(cursor.get());
        while (!operators.isEmpty() && precedence(operator) <= precedence(operators.peek())) {
          Integer right = operands.pop();
          Integer left = operands.pop();
          operands.push(apply(operators.pop(), left, right));
        }
        operators.push(operator);
        cursor.incrementAndGet();
      }
    }
    return operands.pop();
  }

  private Integer readOperand(String expression, AtomicInteger cursor) {
    StringBuilder builder = new StringBuilder();
    while (cursor.get() < expression.length()
        && Character.isDigit(expression.charAt(cursor.get()))) {
      builder.append(expression.charAt(cursor.getAndIncrement()));
    }
    return Integer.valueOf(builder.toString());
  }

  private void skipWhitespace(String expression, AtomicInteger cursor) {
    while (cursor.get() < expression.length()
        && Character.isWhitespace(expression.charAt(cursor.get()))) {
      cursor.incrementAndGet();
    }
  }

  private Integer apply(char operator, int first, int second) {
    switch (operator) {
      case '+':
        return first + second;
      case '-':
        return first - second;
      case '*':
        return first * second;
      case '/':
        return first / second;
    }
    throw new IllegalStateException();
  }

  private int precedence(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
    }
    throw new IllegalStateException();
  }
}
