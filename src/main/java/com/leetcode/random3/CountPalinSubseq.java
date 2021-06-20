package com.leetcode.random3;

public class CountPalinSubseq {

  public int countPalindromicSubsequences(String S) {
    return dfs(S);
  }

  private int dfs(String s) {
    if (s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    if (s.charAt(0) == s.charAt(s.length() - 1)) {
      return dfs(s.substring(1, s.length() - 1));
    } else {
      return dfs(s.substring(1)) + dfs(s.substring(0, s.length() - 1));
    }
  }

}
