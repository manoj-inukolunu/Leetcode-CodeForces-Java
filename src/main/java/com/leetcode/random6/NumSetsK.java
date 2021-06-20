package com.leetcode.random6;

public class NumSetsK {

  int mod = (int) (1e9 + 7);

  private int numberOfSets(int n, int k) {
    Integer[][][] dp = new Integer[n + 1][k + 1][2];
    return dfs(n, 0, k, 1, dp);

  }

  private int dfs(int n, int idx, int k, int isStart, Integer[][][] dp) {
    if (dp[idx][k][isStart] != null) {
      return dp[idx][k][isStart];
    }
    if (k == 0) {
      return 1;
    }
    if (idx >= n) {
      return 0;
    }

    //skip point
    long ret = 0;
    ret += dfs(n, idx + 1, k, isStart, dp);
    if (isStart == 1) {
      ret += dfs(n, idx + 1, k, 0, dp);
    } else {
      ret += dfs(n, idx, k - 1, 1, dp);
    }
    dp[idx][k][isStart] = (int) ret % mod;
    return (int) (ret % mod);
  }

  public static void main(String args[]) {
    NumSetsK n = new NumSetsK();
    System.out.println(n.numberOfSets(10, 2));
  }


}
