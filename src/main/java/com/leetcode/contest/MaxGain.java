package com.leetcode.contest;

import java.util.Stack;

public class MaxGain {

  public int maximumGain(String s, int x, int y) {
    int i = 0, sum = 0;
    while (i < s.length()) {
      char ch = s.charAt(i);
      if (ch == 'a' || ch == 'b') {
        int start = i;
        while (start < s.length() && (s.charAt(start) == 'a' || s.charAt(start) == 'b')) {
          start++;
        }
        sum += solve(s.substring(i, start), x, y);
        i = start;
      } else {
        i++;
      }
    }
    return sum;
  }

  private int solve(String s, int x, int y) {
    Stack<Character> stack = new Stack<>();
    int i = 0, sum = 0;
    while (i < s.length()) {
      char ch = s.charAt(i);
      if (stack.isEmpty()) {
        stack.push(ch);
      } else if (stack.peek() == 'b' && ch == 'a') {
        if (y > x || i == s.length() - 1) {
          stack.pop();
          sum += y;
        } else {
          stack.push(ch);
        }
      } else if (stack.peek() == 'a' && ch == 'b') {
        if (x > y || i == s.length() - 1) {
          stack.pop();
          sum += x;
        } else {
          stack.push(ch);
        }
      } else {
        stack.push(ch);
      }
      i++;
    }
    return sum;
  }

  public static void main(String args[]) {
    MaxGain m = new MaxGain();
    System.out.println(m.maximumGain(
        "aabbabkbbbfvybssbtaobaaaabataaadabbbmakgabbaoapbbbbobaabvqhbbzbbkapabaavbbeghacabamdpaaqbqabbjbababmbakbaabajabasaabbwabrbbaabbafubayaazbbbaababbaaha",
        1926, 4320));
  }

}
