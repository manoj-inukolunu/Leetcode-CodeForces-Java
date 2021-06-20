package com.leetcode.random;

import java.util.Arrays;

public class CanBreak {

  public boolean checkIfCanBreak(String s1, String s2) {

    char[] s1Char = s1.toCharArray();
    char[] s2Char = s2.toCharArray();

    Arrays.sort(s1Char);
    Arrays.sort(s2Char);

    return canBreak(s1Char, s2Char) || canBreak(s2Char, s1Char);

  }

  private boolean canBreak(char[] s, char[] t) {
    for (int i = 0; i < s.length; i++) {
      if (s[i] < t[i]) {
        return false;
      }
    }
    return true;
  }

}
