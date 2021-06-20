package com.leetcode.random10.sixmonths.medium;

import java.util.Stack;

/**
 * @author manoji on 7/30/20.
 */
public class ReversePolishNotation {

  public int evalRPN(String[] tokens) {
    int sum = 0;
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      String curr = tokens[i];
      if (stack.isEmpty()) {
        stack.push(curr);
      } else {
        if (isOperator(curr)) {
          String op2 = stack.pop();
          String op1 = stack.pop();
          stack.push(eval(op1, op2, curr));
        } else {
          stack.push(curr);
        }
      }
    }
    return Integer.parseInt(stack.peek());
  }

  private boolean isOperator(String curr) {
    return curr.equalsIgnoreCase("+") || curr
        .equalsIgnoreCase("*") || curr.equalsIgnoreCase("/") || curr.equalsIgnoreCase("-");
  }

  private String eval(String op1, String op2, String operator) {
    switch (operator) {
      case "*":
        return String.valueOf(Integer.parseInt(op1) * Integer.parseInt(op2));
      case "+":
        return String.valueOf(Integer.parseInt(op1) + Integer.parseInt(op2));
      case "/":
        return String.valueOf((int) Math.floor(Integer.parseInt(op1) / Integer.parseInt(op2)));
      case "-":
        return String.valueOf(Integer.parseInt(op1) - Integer.parseInt(op2));
    }
    return null;
  }

  public static void main(String args[]) {
    String[] rpn = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
    ReversePolishNotation r = new ReversePolishNotation();
    System.out.println(r.evalRPN(rpn));
  }
}
