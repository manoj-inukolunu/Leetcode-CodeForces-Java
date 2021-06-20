package com.leetcode.random5;

import java.util.HashMap;

public class Confusing2 {

  HashMap<Integer, Integer> map = new HashMap<>();
  int count = 0;

  public int confusingNumberII(int N) {
    map.put(0, 0);
    map.put(1, 1);
    map.put(6, 9);
    map.put(8, 8);
    map.put(9, 6);
    int digits = digitsIn(N);
    dfs(new StringBuffer(), 0, digits, N);
    return count;
  }

  private void dfs(StringBuffer buffer, int idx, int digits, int n) {
    if (idx > digits) {
      return;
    }
    if (buffer.length() != 0) {
      if (buffer.charAt(0) == '0' || Long.parseLong(buffer.toString()) > n) {
        return;
      }
    }
    for (int key : map.keySet()) {
      buffer.append(key);
      if (valid(buffer) && Long.parseLong(buffer.toString()) <= n) {
        System.out.println(buffer.toString());
        count++;
      }
      dfs(buffer, idx + 1, digits, n);
      buffer.deleteCharAt(buffer.length() - 1);
    }

  }

  private boolean valid(StringBuffer buffer) {
    int start = 0, end = buffer.length() - 1;
    while (start < buffer.length() && end >= 0) {
      if (Character.getNumericValue(buffer.charAt(start++)) != map.get(Character.getNumericValue(buffer.charAt(end--)))) {
        return true;
      }
    }

    return false;
  }

  private int digitsIn(int num) {
    return (int) Math.floor(Math.log10(num) + 1) == Integer.MIN_VALUE ? 0 : (int) Math.floor(Math.log10(num) + 1);
  }

  public static void main(String args[]) {
    Confusing2 c = new Confusing2();
    System.out.println(c.confusingNumberII(150));
  }

}
