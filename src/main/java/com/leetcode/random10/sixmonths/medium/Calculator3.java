package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator3 {


  public int calculate(String s) {
    s = s.replaceAll(" ", "");
    long val = 0;
    int i = 0;
    Stack<String> stack = new Stack<>();
    while (i < s.length()) {
      if (s.charAt(i) == '(') {
        stack.push(s.charAt(i) + "");
        List<String> list = new ArrayList();
        i++;
        while (!stack.isEmpty()) {
          String top = stack.peek();
          if (top.equalsIgnoreCase(")")) {
            while (!stack.isEmpty() && !stack.peek().equalsIgnoreCase("(")) {
              list.add(0, stack.pop());
            }
            list.remove(list.size() - 1);
            stack.pop();
            val = eval(list);
            list.clear();
            stack.push(val + "");
          } else {
            if (i >= s.length()) {
              break;
            }
            StringBuffer buffer = new StringBuffer();
            i = getBuffer(s, i, buffer);
            stack.push(buffer.toString());
          }
        }
      } else {
        StringBuffer buffer = new StringBuffer();
        i = getBuffer(s, i, buffer);
        stack.push(buffer.toString());
      }
    }
    List<String> list = new ArrayList<>();
    while (!stack.isEmpty()) {
      list.add(0, stack.pop());
    }
    return (int) eval(list);
  }

  private int getBuffer(String s, int i, StringBuffer buffer) {
    while (i < s.length() && Character.isDigit(s.charAt(i))) {
      buffer.append(s.charAt(i++));
    }
    if (buffer.length() == 0) {
      buffer.append(s.charAt(i++));
    }
    return i;
  }

  private long eval(List<String> tokens) {
    int i = 0;
    Stack<String> stack = new Stack<>();
    while (i < tokens.size()) {
      String tok = tokens.get(i);
      if (tok.equals("/")) {
        Long op1 = Long.parseLong(stack.pop());
        Long op2 = Long.parseLong(tokens.get(i + 1));
        stack.push(String.valueOf(op1 / op2));
        i += 2;
      } else if (tok.equals("*")) {
        Long op1 = Long.parseLong(stack.pop());
        Long op2 = Long.parseLong(tokens.get(i + 1));
        stack.push(String.valueOf(op1 * op2));
        i += 2;
      } else {
        stack.push(tok);
        i++;
      }
    }
    tokens.clear();
    while (!stack.isEmpty()) {
      tokens.add(0, stack.pop());
    }
    Long res = 0L;
    i = 0;
    while (i < tokens.size()) {
      if (tokens.get(i).equals("+")) {
        res = res + Long.parseLong(tokens.get(i + 1));
        i += 2;
      } else if (tokens.get(i).equals("-")) {
        res = res - Long.parseLong(tokens.get(i + 1));
        i += 2;
      } else {
        res = Long.parseLong(tokens.get(i));
        i++;
      }
    }
    return res;
  }

  public static void main(String args[]) {
    Calculator3 c = new Calculator3();
    System.out.println(c.calculate("0-2147483648"));
  }
}
