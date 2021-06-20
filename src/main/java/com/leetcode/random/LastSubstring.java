package com.leetcode.random;

public class LastSubstring {

  public String lastSubstring(String s) {
    char max = 'a';
    for (int i = 0; i < s.length(); i++) {
      max = (char) Math.max(max, s.charAt(i));
    }
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == max) {
        return s.substring(i);
      }
    }
    return "";
  }

  public static void main(String args[]) {
    LastSubstring l = new LastSubstring();
    System.out.println(l.lastSubstring("leetcode"));
  }

}
