package com.leetcode.random1;

import java.util.Arrays;

public class MaxTransMinTwo {

  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int[][][] dp = new int[prices.length][2][3];
    for (int[][] row : dp) {
      for (int[] r : row) {
        Arrays.fill(r, -1);
      }
    }

    return recur(prices, 0, 0, 0, dp);
  }

  private int recur(int[] prices, int day, int own, int count, int[][][] dp) {
    if (count > 2) {
      return 0;
    }
    if (day >= prices.length) {
      return 0;
    }
    if (dp[day][own][count] != -1) {
      return dp[day][own][count];
    }
    if (own == 1) {
      int sell = prices[day] + recur(prices, day + 1, 0, count, dp);
      int dontSell = recur(prices, day + 1, 1, count, dp);
      dp[day][own][count] = Math.max(sell, dontSell);
      return Math.max(sell, dontSell);
    } else {
      int buy = -(prices[day]) + recur(prices, day + 1, 1, count + 1, dp);
      int dontBuy = recur(prices, day + 1, 0, count, dp);
      dp[day][own][count] = Math.max(buy, dontBuy);
      return Math.max(buy, dontBuy);
    }
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1};
    MaxTransMinTwo m = new MaxTransMinTwo();
    System.out.println(m.maxProfit(arr));

  }

}
