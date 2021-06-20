package com.leetcode.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author manoji on 6/2/20.
 */
public class BasicCalculator {

  class Token {

    boolean isOperator;
    long val;
    char operator;

    public Token(boolean isOperator, long val, char operator) {
      this.isOperator = isOperator;
      this.val = val;
      this.operator = operator;
    }
  }

  public int calculate(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    s = s.replaceAll(" ", "");
    List<Character> first = Arrays.asList('/', '*');
    List<Character> second = Arrays.asList('+', '-');
    List<Token> tokens = new ArrayList<>();
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);
      boolean isOperator = first.contains(current) || second.contains(current);
      if (isOperator) {
        Token token = new Token(false, Long.parseLong(buffer.toString()), ' ');
        tokens.add(token);
        token = new Token(isOperator, Long.MAX_VALUE, current);
        tokens.add(token);
        buffer.delete(0, buffer.length());
      } else {
        buffer.append(current);
      }
    }
    if (buffer.length() > 0) {
      Token token = new Token(false, Long.parseLong(buffer.toString()), ' ');
      tokens.add(token);
    }
    buffer.delete(0, buffer.length());

    process(tokens, first);
    process(tokens, second);
    return (int) tokens.get(0).val;
  }

  private void process(List<Token> tokens, List<Character> operators) {
    Stack<Token> stack = new Stack<>();
    int i = 0;
    while (i < tokens.size()) {
      Token token = tokens.get(i++);
      if (operators.contains(token.operator)) {
        Token top = stack.pop();
        long eval = getVal(top, tokens.get(i++), token.operator);
        stack.push(new Token(false, eval, ' '));
      } else {
        stack.push(token);
      }
    }
    tokens.clear();
    while (!stack.isEmpty()) {
      tokens.add(0, stack.pop());
    }
  }


  private long getVal(Token op1, Token op2, Character operator) {
    switch (operator) {
      case '+':
        return op1.val + op2.val;
      case '-':
        return op1.val - op2.val;
      case '/':
        return Math.floorDiv(op1.val, op2.val);
      case '*':
        return op1.val * op2.val;
    }
    return -1;
  }

  public static void main(String args[]) {
    BasicCalculator b = new BasicCalculator();
    System.out.println(b.calculate("1+2*5/3+6/4*2"));
  }


}
