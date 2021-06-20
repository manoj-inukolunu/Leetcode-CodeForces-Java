package com.leetcode.random;

public class CoinChange2 {

  public int change(int amount, int[] coins) {

    int[][] dp = new int[coins.length + 1][amount + 1];

    for (int i = 0; i < coins.length; i++) {
      for (int sum = 0; sum <= amount; sum++) {
        if (sum == 0) {
          dp[i][sum] = 1;
        } else {
          int excl = (i == 0) ? 0 : dp[i - 1][sum];
          int incl = (coins[i] > sum) ? 0 : dp[i][sum - coins[i]];
          dp[i][sum] = excl + incl;
        }
      }
    }

    return dp[coins.length - 1][amount];

  }

  public static void main(String args[]) {
    CoinChange2 c = new CoinChange2();
    System.out.println(c.change(10, new int[]{10}));
  }

}
