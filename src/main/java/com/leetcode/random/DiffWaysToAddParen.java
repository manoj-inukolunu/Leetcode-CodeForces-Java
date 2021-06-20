package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToAddParen {

  enum TokenType {
    ADD, SUB, MUL, DIV;
  }

  class Token {

    TokenType tokenType;
    int val;

    public Token(TokenType tokenType, int val) {
      this.tokenType = tokenType;
      this.val = val;
    }

    public boolean isOperator() {
      return tokenType != null;
    }
  }

  public List<Integer> diffWaysToCompute(String input) {
    List<Token> list = tokenize(input);
    return process(list, 0, list.size());
  }

  private List<Integer> process(List<Token> tokens, int start, int end) {
    List<Integer> list = new ArrayList<>();
    if (start >= end) {
      return list;
    }
    if (end - start == 1) {
      list.add(tokens.get(start).val);
      return list;
    }
    for (int i = start; i < end; i++) {
      Token token = tokens.get(i);
      if (token.isOperator()) {
        List<Integer> left = process(tokens, start, i);
        List<Integer> right = process(tokens, i + 1, end);
        for (int j = 0; j < left.size(); j++) {
          for (int k = 0; k < right.size(); k++) {
            addToList(left.get(j), right.get(k), list, token.tokenType);
          }
        }
      }

    }
    return list;
  }


  private List<Token> tokenize(String input) {
    List<Token> list = new ArrayList<>();
    int i = 0;
    while (i < input.length()) {
      StringBuffer buffer = new StringBuffer();
      while (i < input.length() && Character.isDigit(input.charAt(i))) {
        buffer.append(input.charAt(i));
        i++;
      }
      Token token = new Token(null, Integer.parseInt(buffer.toString()));
      list.add(token);
      if (i < input.length()) {
        list.add(new Token(getTokenType(input.charAt(i)), -1));
      }
      i++;
    }
    return list;
  }

  private TokenType getTokenType(char ch) {
    switch (ch) {
      case '+':
        return TokenType.ADD;
      case '/':
        return TokenType.DIV;
      case '*':
        return TokenType.MUL;
      case '-':
        return TokenType.SUB;
      default:
        throw new IllegalArgumentException("Fail");
    }
  }

  private void addToList(Integer left, Integer right, List<Integer> list, TokenType type) {
    switch (type) {
      case ADD:
        list.add(left + right);
        break;
      case DIV:
        list.add(left / right);
        break;
      case MUL:
        list.add(left * right);
        break;
      case SUB:
        list.add(left - right);
        break;
      default:
        throw new IllegalArgumentException("Fail");
    }
  }


  public static void main(String args[]) {
    DiffWaysToAddParen d = new DiffWaysToAddParen();

    System.out.println(d.diffWaysToCompute("2*3-4*5"));
  }

}
