package com.leetcode.random5;

public class MinCutPalin {

  public int minCut(String s) {
    Boolean[][] dp = new Boolean[s.length() + 1][s.length() + 1];
    return dfs(s, 0, s.length() - 1, dp);
  }

  private int dfs(String str, int start, int end, Boolean[][] dp) {
    if (start >= end || isPalin(str, start, end, dp)) {
      return 0;
    }
    int min = Integer.MAX_VALUE;
    for (int i = start; i <= end; i++) {
      if (isPalin(str, start, i, dp)) {
        min = Math.min(min, 1 + dfs(str, i + 1, end, dp));
      }
    }
    return min;
  }

  private boolean isPalin(String str, int start, int end, Boolean[][] dp) {
    if (dp[start][end] != null) {
      return dp[start][end];
    }
    if (str.charAt(start) == str.charAt(end) && dp[start + 1][end - 1] != null) {
      dp[start][end] = true;
      return true;
    } else if (str.charAt(start) != str.charAt(end)) {
      dp[start][end] = false;
      return false;
    }
    while (start < end) {
      if (str.charAt(start) == str.charAt(end)) {
        start++;
        end--;
      } else {
        dp[start][end] = false;
        return false;
      }
    }
    dp[start][end] = true;
    return true;
  }

  public static void main(String args[]) {
    MinCutPalin m = new MinCutPalin();
    System.out.println(m.minCut("ab"));
  }


}
