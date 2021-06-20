package com.leetcode.random5;

public class DelOp {

  public int minDistance(String word1, String word2) {
    Integer[][] dp = new Integer[word1.length()][word2.length()];

    return dfs(word1, 0, word2, 0, dp);
  }

  private int dfs(String str1, int idx1, String str2, int idx2, Integer[][] dp) {
    if (idx1 >= str1.length() && idx2 >= str2.length()) {
      return 0;
    }
    if (idx1 >= str1.length() && idx2 < str2.length()) {
      return str2.length() - idx2 + 1;
    }
    if (idx2 >= str2.length() && idx1 < str1.length()) {
      return str1.length() - idx1 + 1;
    }
    if (dp[idx1][idx2] != null) {
      return dp[idx1][idx2];
    }
    if (str1.charAt(idx1) == str2.charAt(idx2)) {
      int val = dfs(str1, idx1 + 1, str2, idx2 + 1, dp);
      dp[idx1][idx2] = val;
      return val;
    }
    int ret = 1 + Math.min(dfs(str1, idx1 + 1, str2, idx2, dp), dfs(str1, idx1, str2, idx2 + 1, dp));

    dp[idx1][idx2] = ret;
    return ret;
  }



}
