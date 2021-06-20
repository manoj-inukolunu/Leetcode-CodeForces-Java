package com.leetcode.stack;

import java.util.Stack;

public class ScoreOfParenthesis {

  enum TokenType {
    NUMBER, LPAREN, RPAREN;
  }

  class Token {

    TokenType type;
    String val;

    public Token(TokenType type, String val) {
      this.type = type;
      this.val = val;
    }
  }

  public int scoreOfParentheses(String s) {
    int i = 0;
    Stack<Token> stack = new Stack<>();
    while (i < s.length()) {
      char ch = s.charAt(i);
      if (ch == '(' && i + 1 < s.length() && s.charAt(i + 1) == ')') {
        stack.push(new Token(TokenType.NUMBER, "1"));
        i += 2;
      } else if (ch == '(') {
        stack.push(new Token(TokenType.LPAREN, "("));
        i++;
      } else if (ch == ')') {
        int val = 0;
        while (true) {
          if (stack.isEmpty()) {
            break;
          }
          Token curr = stack.pop();
          if (curr.type == TokenType.LPAREN) {
            val = 2 * val;
            stack.push(new Token(TokenType.NUMBER, val + ""));
            break;
          }
          val += Integer.parseInt(curr.val);
        }
        i++;
      }
    }
    Integer ans = 0;
    while (!stack.isEmpty()) {
      ans += Integer.parseInt(stack.pop().val);
    }
    return ans;
  }

  public static void main(String args[]) {
    ScoreOfParenthesis s = new ScoreOfParenthesis();
    System.out.println(s.scoreOfParentheses("()"));
  }
}
