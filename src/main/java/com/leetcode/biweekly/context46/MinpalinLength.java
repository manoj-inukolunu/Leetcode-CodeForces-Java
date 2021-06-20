package com.leetcode.biweekly.context46;

public class MinpalinLength {

  public int longestPalindrome(String word1, String word2) {
    String str = word1 + word2;
    int[][][] dp = new int[str.length()][str.length()][2];
    int val = lps(str, word1, word2, 0, str.length() - 1, 0, dp);
    return val;
  }

  private int lps(String str, String a, String b, int start, int end, int incl, int[][][] dp) {
    if (start > end) {
      return 0;
    }
    if (start == end) {
      return incl == 1 ? 1 : 0;
    }
    if (str.charAt(start) == str.charAt(end)) {
      int curr = incl;
      if (curr != 1) {
        curr = start < a.length() && end >= a.length() ? 1 : 0;
      }
      int val = 0;
      if (curr == 0) {
        if (start > a.length() || end < b.length()) {
          val = 0;
        }
      } else {
        val = 2 + lps(str, a, b, start + 1, end - 1, curr, dp);
      }
      dp[start][end][incl] = val;
      return val;
    }
    dp[start][end][incl] = Math.max(lps(str, a, b, start + 1, end, incl, dp), lps(str, a, b, start, end - 1, incl, dp));
    return dp[start][end][incl];
  }

  public static void main(String args[]) {
    MinpalinLength m = new MinpalinLength();
    System.out.println(m.longestPalindrome("cacb", "cbba"));
  }

}
