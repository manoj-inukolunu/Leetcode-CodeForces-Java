package com.leetcode.random;

public class TernaryExpParser {

  public String parseTernary(String expression) {
    return parse(expression, 0, expression.length());
  }

  private String parse(String exp, int start, int end) {
    if (end - start == 1) {
      return exp.charAt(start) + "";
    }
    int idx = exp.indexOf('?');
    String toEval = exp.substring(start, idx);
    int count = 0;
    int i = idx;
    String first = null, second = null;
    while (i < exp.length()) {
      char ch = exp.charAt(i);
      if (ch == '?') {
        count++;
      } else if (ch == ':') {
        count--;
      }
      if (count == 0) {
        first = exp.substring(idx + 1, i);
        second = exp.substring(i + 1);
        break;
      }
      i++;
    }
    if (evaluate(toEval)) {
      return parse(first, 0, first.length());
    } else {
      return parse(second, 0, second.length());
    }
  }

  private boolean evaluate(String str) {
    return str.equals("T");
  }

  public static void main(String args[]) {
    TernaryExpParser t = new TernaryExpParser();
    System.out.println(t.parseTernary("T?T?F:5:3"));
  }

}
