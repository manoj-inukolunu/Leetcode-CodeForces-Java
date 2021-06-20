package com.leetcode.random6;

public class CoungHomogeneous {

  public int countHomogenous(String s) {
    int mod = (int) Math.pow(10, 9) + 7;
    int start = 0, end = 0;
    long count = 0;
    while (end < s.length()) {
      while (end + 1 < s.length() && s.charAt(end) == s.charAt(end + 1)) {
        end++;
      }
      int len = end - start + 1;
      count += ((long) len * (len + 1)) / 2;
      count %= mod;
      start = end + 1;
      end++;
    }
    return (int) count % mod;
  }

  public static void main(String args[]) {
    CoungHomogeneous c = new CoungHomogeneous();
    System.out.println(c.countHomogenous("zzzzz"));
  }

}
