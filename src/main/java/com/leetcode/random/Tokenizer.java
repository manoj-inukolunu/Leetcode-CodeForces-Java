package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

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

  public static void main(String args[]) {
    Tokenizer t = new Tokenizer();
    System.out.println(t.tokenize("(B2O39He17BeBe49)39"));
  }

}
