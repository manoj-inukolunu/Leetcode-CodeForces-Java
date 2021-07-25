package com.leetcode.hard;

import java.util.Arrays;

public class LC600 {
    String num;

    public int findIntegers(int n) {
        num = Integer.toBinaryString(n);
        int len = Integer.toBinaryString(n).length();
        int[][][] dp = new int[50][2][2];
        for (int[][] row : dp) {
            for (int[] d : row) {
                Arrays.fill(d, -1);
            }
        }
        return solve(len, 0, 1, 0, dp) + solve(len, 1, 1, 1, dp);
    }

    private int solve(int len, int prev, int idx, int equal, int[][][] dp) {
        if (idx >= len) {
            return 1;
        }
        if (dp[idx][prev][equal] != -1) {
            return dp[idx][prev][equal];
        }
        int count;
        if (prev == 1) {
            if (num.charAt(idx) == '1') {
                count = solve(len, 0, idx + 1, 0, dp);
            } else {
                count = solve(len, 0, idx + 1, equal, dp);
            }
        } else if (equal == 1) {
            if (num.charAt(idx) == '1') {
                count = solve(len, 1, idx + 1, 1, dp) + solve(len, 0, idx + 1, 0, dp);
            } else {
                count = solve(len, 0, idx + 1, 1, dp);
            }
        } else {
            count = solve(len, 0, idx + 1, 0, dp) + solve(len, 1, idx + 1, 0, dp);
        }
        dp[idx][prev][equal] = count;
        return count;
    }

    public static void main(String args[]) {
        LC600 l = new LC600();
        int powe12 = (int) Math.pow(10, 9);
        int val = 15;
        System.out.println(l.findIntegers(powe12));
    }
}
