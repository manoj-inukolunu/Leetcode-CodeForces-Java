package com.leetcode.random;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LC11625 {

  public String findLexSmallestString(String s, int a, int b) {
    Queue<String> queue = new LinkedList<>();
    queue.add(s);
    HashSet<String> visited = new HashSet<>();
    String min = s;
    while (!queue.isEmpty()) {
      String curr = queue.poll();
      if (curr.compareTo(min) <= 0) {
        min = curr;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        for (String next : nextStr(curr, a, b)) {
          queue.add(next);
        }
      }
    }
    return min;
  }

  private String[] nextStr(String curr, int a, int b) {
    StringBuffer buffer = new StringBuffer(curr);
    for (int i = 0; i < buffer.length(); i++) {
      if (i % 2 != 0) {
        buffer.setCharAt(i, getNextNum(buffer.charAt(i), a));
      }
    }
    String[] res = new String[]{buffer.toString(), rightrotate(buffer.toString(), b)};

    return res;
  }

  String leftrotate(String str, int d) {
    return str.substring(d) + str.substring(0, d);
  }

  String rightrotate(String str, int d) {
    return leftrotate(str, str.length() - d);
  }

  private char getNextNum(char charAt, int a) {
    int val = Character.getNumericValue(charAt) + a;
    if (val > 9) {
      return (char) ((val - 10) + '0');
    } else {
      return (char) (val + '0');
    }
  }

  public static void main(String args[]) {
    LC11625 l = new LC11625();
    System.out.println(l.findLexSmallestString("43987654", 7, 3));
  }

}
