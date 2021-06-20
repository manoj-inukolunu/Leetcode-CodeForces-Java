/*
package com.leetcode.random;

import java.util.Stack;

 class TreeNode extends Node {

  String[] postfix;

  public TreeNode(String[] postfix) {
    this.postfix = postfix;
  }

  @Override
  public int evaluate() {
    Stack<String> stack = new Stack<>();
    int i = 0;
    while (i < postfix.length) {
      if (stack.isEmpty()) {
        stack.push(postfix[i]);
      } else {
        String top = stack.peek();
        if (isOperator(top)) {
          int second = Integer.parseInt(stack.pop());
          int first = Integer.parseInt(stack.pop());
          stack.push(apply(first, second, top));
        }
      }
    }
    return Integer.parseInt(stack.pop());
  }

  private String apply(int first, int second, String top) {
    switch (top) {
      case "+":
        return String.valueOf(first + second);
      case "-":
        return String.valueOf(first - second);
      case "*":
        return String.valueOf(first * second);
      case "/":
        return String.valueOf(first / second);
    }
    return null;
  }

  private boolean isOperator(String top) {
    return top.equalsIgnoreCase("+") || top.equalsIgnoreCase("-") || top.equalsIgnoreCase("*") || top
        .equalsIgnoreCase("/");
  }
}

abstract class Node {

  public abstract int evaluate();
  // define your fields here
};


*/
/**
 * This is the TreeBuilder class. You can treat it as the driver code that takes the postinfix input and returns the expression tree represnting it as
 * a Node.
 *//*


public class TreeBuilder {

  Node buildTree(String[] postfix) {
    return new TreeNode(postfix);
  }
};
*/
