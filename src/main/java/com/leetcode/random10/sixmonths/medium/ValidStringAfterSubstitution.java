package com.leetcode.random10.sixmonths.medium;

import java.util.Stack;

public class ValidStringAfterSubstitution {

  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    int i = 0;
    while (i < s.length()) {
      if (stack.isEmpty()) {
        stack.push(s.charAt(i));
      } else {
        Character top = stack.peek();
        if (top == 'c') {
          int count = 0;
          StringBuffer buffer = new StringBuffer();
          while (count < 3 && !stack.isEmpty()) {
            buffer.insert(0, stack.pop());
            count++;
          }
          if (!buffer.toString().equalsIgnoreCase("abc")) {
            while (buffer.length() != 0) {
              stack.push(buffer.charAt(0));
              buffer.deleteCharAt(0);
            }

          }
          stack.push(s.charAt(i));
        } else {
          stack.push(s.charAt(i));
        }
      }
      i++;
    }
    StringBuffer buffer = new StringBuffer();
    while (!stack.isEmpty()) {
      buffer.insert(0, stack.pop());
    }
    return buffer.toString().equals("abc");
  }

  public static void main(String args[]) {
    String str = "";
    ValidStringAfterSubstitution v = new ValidStringAfterSubstitution();

    System.out.println(v.isValid(str));
  }

}
