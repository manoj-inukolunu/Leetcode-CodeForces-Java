package com.leetcode.hard;

import java.util.Arrays;

public class LC1420 {

    int mod = (int) Math.pow(10, 9) + 7;

    public int numOfArrays(int n, int m, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        for (int[][] row : dp) {
            for (int[] r : row) {
                Arrays.fill(r, -1);
            }
        }
        return solve(0, 0, 0, n, k, m, dp);
    }

    private int solve(int max, int idx, int curr, int n, int k, int m, int[][][] dp) {
        if (curr > k) {
            return 0;
        }
        if (idx >= n) {
            return curr == k ? 1 : 0;
        }
        if (dp[idx][max][curr] != -1) {
            return dp[idx][max][curr];
        }
        long count = 0;
        for (int i = 1; i <= m; i++) {
            if (i <= max) {
                count += solve(max, idx + 1, curr, n, k, m, dp);
                count %= mod;
            } else {
                count += solve(i, idx + 1, curr + 1, n, k, m, dp);
                count %= mod;
            }

        }
        return dp[idx][max][curr] = (int) count % mod;
    }

    public static void main(String[] args) {
        LC1420 l = new LC1420();
        System.out.println(l.numOfArrays(50, 100, 50));
    }
}
