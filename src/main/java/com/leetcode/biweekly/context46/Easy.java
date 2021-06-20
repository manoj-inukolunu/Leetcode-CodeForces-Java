package com.leetcode.biweekly.context46;

import java.util.HashSet;

public class Easy {

  public String longestNiceSubstring(String s) {
    int max = Integer.MIN_VALUE;
    String ans = "";
    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 1; j <= s.length(); j++) {
        String str = s.substring(i, j);
        if (isNice(str) && str.length() > max) {
          max = str.length();
          ans = str;
        }
      }
    }
    return ans;
  }

  private boolean isNice(String s) {
    HashSet<Character> set = new HashSet();
    for (int i = 0; i < s.length(); i++) {
      set.add(s.charAt(i));
    }
    for (char ch : set) {
      if (Character.isLowerCase(ch) && !set.contains(Character.toUpperCase(ch))) {
        return false;
      }
      if (Character.isUpperCase(ch) && !set.contains(Character.toLowerCase(ch))) {
        return false;
      }

    }
    return true;

  }

  public static void main(String args[]) {
    Easy e = new Easy();
    System.out.println(e.longestNiceSubstring("jcJ"));
  }

}
