package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class CountAtoms {

  enum TokenType {
    ELEMENT, LPAREN, RPAREN, NUMBER
  }

  class Token {

    TokenType type;
    int val;
    String name;

    @Override
    public String toString() {
      return "Token{" +
          "type=" + type +
          ", val=" + val +
          ", name='" + name + '\'' +
          '}';
    }

    public Token(TokenType type, int val, String name) {
      this.type = type;
      this.val = val;
      this.name = name;
    }
  }

  public List<Token> tokenize(String str) {
    StringBuffer buffer = new StringBuffer(str);
    List<Token> list = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      char ch = str.charAt(i);
      if (ch == '(') {
        list.add(new Token(TokenType.LPAREN, -1, null));
        i++;
      } else if (ch == ')') {
        list.add(new Token(TokenType.RPAREN, -1, null));
        i++;
      } else if (Character.isDigit(ch)) {
        StringBuffer temp = new StringBuffer();
        while (i < buffer.length() && Character.isDigit(buffer.charAt(i))) {
          temp.append(buffer.charAt(i));
          i++;
        }
        list.add(new Token(TokenType.NUMBER, Integer.parseInt(temp.toString()), ""));
      } else {
        StringBuffer temp = new StringBuffer();
        temp.append(buffer.charAt(i));
        while (true) {
          i++;
          if (i < buffer.length() && !Character.isUpperCase(buffer.charAt(i)) && Character.isAlphabetic(buffer.charAt(i))) {
            temp.append(buffer.charAt(i));
          } else {
            break;
          }
        }
        list.add(new Token(TokenType.ELEMENT, -1, temp.toString()));
      }
    }
    List<Token> ans = new ArrayList<>();
    for (int j = 0; j + 1 < list.size(); j++) {
      if (list.get(j).type == TokenType.ELEMENT && list.get(j + 1).type != TokenType.NUMBER) {
        ans.add(list.get(j));
        ans.add(new Token(TokenType.NUMBER, 1, ""));
      } else {
        ans.add(list.get(j));
      }
    }
    ans.add(list.get(list.size() - 1));
    if (ans.get(ans.size() - 1).type == TokenType.ELEMENT) {
      ans.add(new Token(TokenType.NUMBER, 1, ""));
    }
    return ans;
  }

  TreeMap<String, Integer> map = new TreeMap<>();

  public String countOfAtoms(String formula) {
    List<Token> tokens = tokenize(formula);
    Stack<Token> stack = new Stack<>();
    int i = 0;
    while (i < tokens.size()) {
      stack.push(tokens.get(i));
      if (stack.peek().type == TokenType.RPAREN) {
        Stack<Token> tempStack = new Stack<>();
        int mul = 1;
        if (i + 1 < tokens.size() && tokens.get(i + 1).type == TokenType.NUMBER) {
          mul = tokens.get(i + 1).val;
        }
        while (stack.peek().type != TokenType.LPAREN) {
          Token curr = stack.pop();
          if (curr.type == TokenType.NUMBER) {
            tempStack.push(new Token(TokenType.NUMBER, curr.val * mul, ""));
          } else if (!isParenToken(curr)) {
            tempStack.push(curr);
          }
        }
        stack.pop();
        while (!tempStack.isEmpty()) {
          stack.push(tempStack.pop());
        }
        if (mul == 1) {
          i++;
        } else {
          i += 2;
        }
      } else {
        i++;
      }
    }
    while (!stack.isEmpty()) {
      Token num = stack.pop();
      Token elem = stack.pop();
      map.put(elem.name, map.getOrDefault(elem.name, 0) + num.val);
    }

    StringBuffer temp = new StringBuffer();
    for (String key : map.keySet()) {
      temp.append(key);
      if (map.get(key) != 1) {
        temp.append(map.get(key));
      }
    }
    return temp.toString();
  }


  private boolean isParenToken(Token curr) {
    return curr.type == TokenType.LPAREN || curr.type == TokenType.RPAREN;
  }

  public static void main(String args[]) {
    CountAtoms c = new CountAtoms();
    System.out.println(c.countOfAtoms("(B2O39He17BeBe49)39"));
  }


}
