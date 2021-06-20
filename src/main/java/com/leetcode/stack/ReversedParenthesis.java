package com.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversedParenthesis {


  public String reverseParentheses(String s) {
    if (s.length() == 0) {
      return "";
    }
    Stack<Character> stack = new Stack<>();
    int i = 0;

    while (i < s.length()) {
      if (stack.isEmpty()) {
        stack.push(s.charAt(i));
      } else {
        Character top = stack.peek();
        if (top == ')') {
          stack.pop();
          List<Character> rev = new ArrayList<>();
          while (stack.peek() != '(') {
            rev.add(stack.pop());
          }
          stack.pop();
          int j = 0;
          while (j < rev.size()) {
            stack.push(rev.get(j++));
          }
          stack.push(s.charAt(i));
        } else {
          stack.push(s.charAt(i));
        }
      }
      i++;
    }
    StringBuffer buffer = new StringBuffer();
    if (stack.peek() == ')') {
      stack.pop();
      List<Character> temp = new ArrayList<>();
      while (stack.peek() != '(') {
        Character ch = stack.pop();
        temp.add(ch);
      }
      stack.pop();
      while (!stack.isEmpty()) {
        buffer.insert(0, stack.pop());
      }
      for (int j = 0; j < temp.size(); j++) {
        buffer.append(temp.get(j));
      }
    } else {
      while (!stack.isEmpty()) {
        Character ch = stack.pop();
        buffer.insert(0, ch);
      }
    }
    return buffer.toString();
  }

  public static void main(String args[]) {
    ReversedParenthesis r = new ReversedParenthesis();
    System.out.println(r.reverseParentheses("co(de(fight)s)"));
  }

}
