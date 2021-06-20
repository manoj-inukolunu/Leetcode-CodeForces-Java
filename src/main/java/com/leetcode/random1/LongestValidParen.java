package com.leetcode.random1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LongestValidParen {

  class Token {

    int len;
    char ch;

    public Token(int len, char ch) {
      this.len = len;
      this.ch = ch;
    }

    public boolean isOpenParen() {
      return this.ch == '(';
    }

    public boolean isNum() {
      return this.ch == ' ';
    }
  }


  public int longestValidParentheses(String s) {
    Stack<Token> stack = new Stack<>();
    int i = 0, max = 0;
    while (i < s.length()) {
      char ch = s.charAt(i);
      if (stack.isEmpty() || ch != ')') {
        stack.push(new Token(0, ch));
      } else {
        int curr = 0;
        while (!stack.isEmpty() && !stack.peek().isOpenParen()) {
          Token popped = stack.pop();
          curr += popped.len;
        }
        if (!stack.isEmpty()) {
          Token popped = stack.pop();
          if (popped.isOpenParen() && curr == 0) {
            curr = 2;
          } else {
            curr += 2;
          }
          max = Math.max(curr, max);
          stack.push(new Token(curr, ' '));
        } else {
          max = Math.max(curr, max);
          //open paren not encountered invalid
          stack.push(new Token(0, ch));
        }
      }
      i++;
    }
    List<Token> list = new ArrayList<>();
    while (!stack.isEmpty()) {
      list.add(0, stack.pop());
    }
    int[] dp = new int[list.size()];
    for (int j = 0; j < list.size(); j++) {
      if (list.get(j).isNum()) {
        dp[j] = list.get(j).len;
      }
      if (j - 1 >= 0 && list.get(j - 1).isNum() && list.get(j).isNum()) {
        dp[j] += dp[j - 1];
      }
      max = Math.max(dp[j], max);
    }
    return max;
  }


  public static void main(String args[]) {
    String s = "()(())";
    String s1 = "(()";
    String s2 = ")()())";
    String s3 = "()()";
    String s4 = ")()())()()(";
    LongestValidParen l = new LongestValidParen();
    System.out.println(l.longestValidParentheses(s4));
  }

}
