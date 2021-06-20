package com.leetcode.string;

import java.util.HashMap;

public class CanConvertK {

  public boolean canConvertString(String s, String t, int k) {
    if (s.length() != t.length()) {
      return false;
    }
    HashMap<Integer, Integer> used = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char sChar = s.charAt(i);
      char tChar = t.charAt(i);
      if (sChar != tChar) {
        int diff = tChar - sChar + (tChar < sChar ? 26 : 0);
        if (diff + used.getOrDefault(diff, 0) * 26 > k) {
          return false;
        }
        used.put(diff, used.getOrDefault(diff, 0) + 1);
      }
    }
    return true;
  }

  public static void main(String args[]) {
    CanConvertK c = new CanConvertK();
    System.out.println(c.canConvertString("abc", "bcd", 10));
  }

}
