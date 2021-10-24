package com.leetcode.hard;

import java.util.Arrays;

public class LC1866 {

    int mod = (int) Math.pow(10, 9) + 7;

    public int rearrangeSticks(int n, int k) {
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(n, k, dp);
    }

    private int solve(int n, int k, int[][] dp) {
        if (n == 0) {
            return k == 0 ? 1 : 0;
        }
        if (dp[n][k] != -1) {
            return dp[n][k];
        }
        long ways = solve(n - 1, k - 1, dp);
        ways %= mod;
        ways += (long) solve(n - 1, k, dp) * (n - 1);
        ways %= mod;
        return dp[n][k] = (int) ways % mod;
    }

    public static void main(String[] args) {
        LC1866 l = new LC1866();
        System.out.println(l.rearrangeSticks(20, 11));
    }
}
