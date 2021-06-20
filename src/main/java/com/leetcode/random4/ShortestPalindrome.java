package com.leetcode.random4;

import java.util.Arrays;

public class ShortestPalindrome {

  int solve(String str) {
    String temp = str + '?';
    str = reverse(str);
    temp += str;
    int n = temp.length();
    int[] lps = new int[n];
    Arrays.fill(lps, 0);
    for (int i = 1; i < n; i++) {
      int len = lps[i - 1];
      while (len > 0 && temp.charAt(len) !=
          temp.charAt(i)) {
        len = lps[len - 1];
      }
      if (temp.charAt(i) == temp.charAt(len)) {
        len++;
      }
      lps[i] = len;
    }
    return temp.substring(0, lps[n - 1]).length();
  }

  String sP(String s) {
    int n = s.length();
    String rev = new StringBuffer(s).reverse().toString();
    for (int i = 0; i < n; i++) {
      if (s.substring(0, n - i) == rev.substring(i)) {
        return rev.substring(0, i) + s;
      }
    }
    return "";
  }

  static String reverse(String input) {
    char[] a = input.toCharArray();
    int l, r = a.length - 1;
    for (l = 0; l < r; l++, r--) {
      char temp = a[l];
      a[l] = a[r];
      a[r] = temp;
    }
    return String.valueOf(a);
  }

  public String shortestPalindrome(String s) {
    int longestPrefix = solve(s);
    return new StringBuffer(s.substring(longestPrefix)).reverse().toString() + s;
  }

  public static void main(String args[]) {
    ShortestPalindrome s = new ShortestPalindrome();
    System.out.println(s.shortestPalindrome("abcd"));
  }

}
