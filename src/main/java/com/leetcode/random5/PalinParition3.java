package com.leetcode.random5;

public class PalinParition3 {

  public int palindromePartition(String s, int k) {
    Integer[][] pDp = new Integer[s.length()][s.length()];
    Integer[][][] dp = new Integer[s.length()][s.length()][k + 1];
    return dfs(s, 0, s.length() - 1, k, pDp, dp);
  }

  private int dfs(String str, int start, int end, int k, Integer[][] pDp, Integer[][][] dp) {
    if (start > end) {
      return Integer.MAX_VALUE;
    }
    if (k == 1) {
      return getMin(str, start, end, pDp);
    }
    if (dp[start][end][k] != null) {
      return dp[start][end][k];
    }
    Integer min = Integer.MAX_VALUE;
    for (int i = start; i <= end; i++) {
      int left = getMin(str, start, i, pDp);
      int right = dfs(str, i + 1, end, k - 1, pDp, dp);
      if (right != Integer.MAX_VALUE) {
        min = Math.min(left + right, min);
      }

    }
    dp[start][end][k] = min;
    return min;
  }

  private int getMin(String str, int start, int end, Integer[][] pDp) {
    if (start == end) {
      return 0;
    }
    if (end - start == 1) {
      return str.charAt(start) == str.charAt(end) ? 0 : 1;
    }
    if (pDp[start][end] != null) {
      return pDp[start][end];
    }
    if (str.charAt(start) == str.charAt(end)) {
      int val = getMin(str, start + 1, end - 1, pDp);
      pDp[start][end] = val;
      return val;
    }
    int val = getMin(str, start + 1, end - 1, pDp);
    if (val != Integer.MAX_VALUE) {
      pDp[start][end] = val + 1;
    } else {
      pDp[start][end] = val;
    }

    return val;
  }

  public static void main(String args[]) {
    PalinParition3 p = new PalinParition3();
    System.out.println(p.palindromePartition("leetcode", 8));
  }

}
