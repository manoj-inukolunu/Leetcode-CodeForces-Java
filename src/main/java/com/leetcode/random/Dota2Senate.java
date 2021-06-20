package com.leetcode.random;

import java.util.Stack;

public class Dota2Senate {

  public String predictPartyVictory(String s) {
    StringBuffer buff = new StringBuffer(s);
    Stack<Character> stack = new Stack<>();
    int rad = 0, dire = 0;
    while (true) {
      int i = 0;
      while (i < buff.length()) {
        char ch = buff.charAt(i);
        if (ch == 'R') {
          if (dire == 0) {
            stack.push(ch);
            rad++;
          } else {
            dire--;
          }
        } else if (ch == 'D') {
          if (rad == 0) {
            stack.push(ch);
            dire++;
          } else {
            rad--;
          }
        }
        i++;
      }
      buff.setLength(0);
      boolean d = false, r = false;
      while (!stack.isEmpty()) {
        char ch = stack.pop();
        if (ch == 'D') {
          d = true;
        } else {
          r = true;
        }
        buff.insert(0, ch);
      }
      if (r && !d) {
        return "Radiant";
      } else if (d && !r) {
        return "Dire";
      }
    }
  }

  public static void main(String args[]) {
    String str = "RDD";
    Dota2Senate d = new Dota2Senate();
    System.out.println(d.predictPartyVictory(str));
  }

}
