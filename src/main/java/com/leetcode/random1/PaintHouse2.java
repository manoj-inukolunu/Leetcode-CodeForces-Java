package com.leetcode.random1;

public class PaintHouse2 {

  public int minCostII(int[][] costs) {

    int[][] dp = new int[costs.length][];
    for (int i = 0; i < costs[0].length; i++) {
      dp[0][i] = costs[0][i];
    }

    for (int i = 1; i < costs.length; i++) {
      for (int k = 0; k < costs[i].length; k++) {
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < dp[i - 1].length; j++) {
          if (j != k) {
            min = Math.min(dp[i - 1][j], min);
          }
        }
        dp[i][k] = min + costs[i][k];
      }
    }
    int cost = Integer.MAX_VALUE;
    for (int i = 0; i < dp[0].length; i++) {
      cost = Math.min(cost, dp[dp.length - 1][i]);
    }
    return cost;
  }

}
