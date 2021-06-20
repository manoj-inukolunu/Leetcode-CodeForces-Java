package com.leetcode.random1;

import java.util.Arrays;

public class MaxProfitCoolDown {

  public int maxProfit(int[] prices, int fee) {
    if (prices.length == 0) {
      return 0;
    }
    int[][] dp = new int[prices.length][2];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    return recur(prices, 0, 0, fee, dp);
  }

  private int recur(int[] prices, int day, int own, int fee, int[][] dp) {
    if (day >= prices.length) {
      return 0;
    }
    if (dp[day][own] != -1) {
      return dp[day][own];
    }
    if (own == 1) {
      int sell = prices[day] + recur(prices, day + 2, 0, fee, dp);
      int dontSell = recur(prices, day + 1, 1, fee, dp);
      dp[day][own] = Math.max(sell, dontSell);
      return Math.max(sell, dontSell);
    } else {
      int buy = -(prices[day] + fee) + recur(prices, day + 1, 1, fee, dp);
      int dontBuy = recur(prices, day + 1, 0, fee, dp);
      dp[day][own] = Math.max(buy, dontBuy);
      return Math.max(buy, dontBuy);
    }
  }

}
