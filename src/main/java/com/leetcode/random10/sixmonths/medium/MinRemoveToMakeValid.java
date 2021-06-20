package com.leetcode.random10.sixmonths.medium;

import java.util.Stack;

/**
 * @author manoji on 7/28/20.
 */
public class MinRemoveToMakeValid {

  class Pair {

    Integer index;
    Character character;

    public Pair(Integer index, Character character) {
      this.index = index;
      this.character = character;
    }
  }

  public String minRemoveToMakeValid(String s) {
    Stack<Pair> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      Character ch = s.charAt(i);
      if (ch == '(') {
        stack.push(new Pair(i, ch));
      } else if (ch == ')' && !stack.isEmpty() && stack.peek().character == '(') {
        stack.pop();
      } else if (ch == ')') {
        stack.push(new Pair(i, ch));
      }
    }

    StringBuffer buffer = new StringBuffer(s);
    while (!stack.isEmpty()) {
      buffer.deleteCharAt(stack.pop().index);
    }

    return buffer.toString();
  }

  public static void main(String args[]) {
    MinRemoveToMakeValid m = new MinRemoveToMakeValid();

    System.out.println(m.minRemoveToMakeValid("(a(b(c)d)"));
  }

}
