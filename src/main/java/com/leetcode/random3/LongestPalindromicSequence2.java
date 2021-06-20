package com.leetcode.random3;

public class LongestPalindromicSequence2 {


  public int longestPalindromeSubseq(String s) {
    Integer[][] dp = new Integer[s.length() + 1][s.length() + 1];
    return dfs(s, 0, s.length() - 1, ' ', ' ', dp);
  }

  private int dfs(String str, int start, int end, char pStart, char pEnd, Integer[][] dp) {
    if (start >= end) {
      return 0;
    }
    if (dp[start][end] != null) {
      return dp[start][end];
    }

    if (str.charAt(start) == str.charAt(end) && str.charAt(start) != pStart && str.charAt(end) != pEnd) {
      dp[start][end] = 2 + dfs(str, start + 1, end - 1, str.charAt(start), str.charAt(end), dp);
      return dp[start][end];
    } else {
      dp[start][end] = Math.max(dfs(str, start + 1, end, pStart, pEnd, dp), dfs(str, start, end - 1, pStart, pEnd, dp));
      return dp[start][end];
    }
  }

  public static void main(String args[]) {
    LongestPalindromicSequence2 l = new LongestPalindromicSequence2();
    System.out.println(l.longestPalindromeSubseq("dcbccacdb"));
  }

}
