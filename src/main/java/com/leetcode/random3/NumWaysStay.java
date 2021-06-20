package com.leetcode.random3;

public class NumWaysStay {


  int mod = (int) (Math.pow(10, 9) + 7);

  public int numWays(int steps, int arrLen) {
    int[][] dp = new int[(steps / 2) + 1][steps + 1];
    int maxPos = Math.min((steps / 2) + 1, arrLen);
    System.out.println(maxPos);
    return dfs(0, steps, arrLen, dp, maxPos);

  }

  private int dfs(int pos, int steps, int len, int[][] dp, int maxPos) {
    if (pos > steps) {
      return Integer.MAX_VALUE;
    }
    if (steps < 0) {
      return Integer.MAX_VALUE;
    }
    if (pos == 0 && steps == 0) {
      return 1;
    }
    if (dp[pos][steps] != 0) {
      return dp[pos][steps];
    }
    int count = 0;
    if (pos + 1 < len && pos + 1 < maxPos && pos + 1 < steps) {
      int val = dfs(pos + 1, steps - 1, len, dp, maxPos);
      if (val != Integer.MAX_VALUE) {
        count = val % mod;
      }
    }
    if (pos - 1 >= 0) {
      int val = dfs(pos - 1, steps - 1, len, dp, maxPos);
      if (val != Integer.MAX_VALUE) {
        count = count % mod + val % mod;
      }
    }
    int val = dfs(pos, steps - 1, len, dp, maxPos);
    if (val != Integer.MAX_VALUE) {
      count = count % mod + val % mod;
    }
    dp[pos][steps] = count % mod;
    return count % mod;
  }

  public static void main(String args[]) {
    NumWaysStay n = new NumWaysStay();
    System.out.println(n.numWays(5000, 1321321));
  }

}
