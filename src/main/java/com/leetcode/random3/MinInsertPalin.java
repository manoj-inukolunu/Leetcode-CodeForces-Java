package com.leetcode.random3;

public class MinInsertPalin {

  public int minInsertions(String s) {
    if (s.length() == 0 || s.length() == 1) {
      return 0;
    }
    if (s.charAt(0) == s.charAt(s.length() - 1)) {
      return minInsertions(s.substring(1, s.length() - 1));
    }
    int leftInsert = 1 + minInsertions(s.substring(0, s.length() - 1));
    int rightInsert = 1 + minInsertions(s.substring(1));

    return Math.min(leftInsert, rightInsert);
  }

}
