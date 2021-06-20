package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator {

  public int calculate(String s) {
    s = s.replaceAll(" ", "");
    int val = 0, i = 0;
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
    return eval(list);
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

  private int eval(List<String> list) {
    int res = 0, i = 0;
    while (i < list.size()) {
      if (list.get(i).equals("+")) {
        res = res + Integer.parseInt(list.get(i + 1));
        i += 2;
      } else if (list.get(i).equals("-")) {
        res = res - Integer.parseInt(list.get(i + 1));
        i += 2;
      } else {
        res = Integer.parseInt(list.get(i));
        i++;
      }
    }
    return res;
  }


  public static void main(String args[]) {
    BasicCalculator b = new BasicCalculator();
    System.out.println(b.calculate("2147483647"));
  }

}
