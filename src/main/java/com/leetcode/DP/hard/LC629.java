package com.leetcode.DP.hard;

import java.util.Arrays;

public class LC629 {
    int mod = (int) Math.pow(10, 9) + 7;

    public int kInversePairs(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return go(0, k, n, dp);
    }

    private int go(int size, int k, int n, int[][] dp) {
        if (k == 0) {
            return 1;
        }
        if (k < 0 || size >= n) {
            return 0;
        }
        if (dp[size][k] != -1) {
            return dp[size][k];
        }
        long count = 0;
        //put at size+1 location
        count += go(size + 1, k, n, dp);
        count %= mod;
        int numPairs = 0;
        for (int i = size; i > 0; i--) {
            numPairs++;
            if (numPairs <= k) {
                count += go(size + 1, k - numPairs, n, dp);
                count %= mod;
            } else {
                break;
            }
        }
        return dp[size][k] = (int) (count % mod);
    }

    public static void main(String[] args) {
        LC629 l = new LC629();
        System.out.println(l.kInversePairs(1000, 100));
    }
}
