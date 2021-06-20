package com.leetcode.biweekly.context46;

import java.util.Arrays;

public class RestoreArray {

  int mod = (int) Math.pow(10, 9) + 7;

  public int numberOfArrays(String s, int k) {
    int[] dp = new int[s.length() + 1];
    Arrays.fill(dp, -1);
    return dfs(s, 0, k, dp);
  }

  private int dfs(String s, int idx, int k, int[] dp) {
    if (idx >= s.length()) {
      return 1;
    }
    if (s.charAt(idx) == '0') {
      return 0;
    }
    if (dp[idx] != -1) {
      return dp[idx];
    }
    int ways = 0;
    StringBuffer buffer = new StringBuffer();
    for (int i = idx; i < s.length(); i++) {
      buffer.append(s.charAt(i));
      if (Integer.parseInt(buffer.toString()) <= k) {
        int val = dfs(s, i + 1, k, dp);
        if (val != 0) {
          ways = (ways % mod + val % mod) % mod;
        }
      }
    }
    dp[idx] = ways % mod;
    return ways % mod;
  }

  public static void main(String args[]) {
    RestoreArray r = new RestoreArray();
    System.out.println(r.numberOfArrays("1234567890", 90));
  }

}
