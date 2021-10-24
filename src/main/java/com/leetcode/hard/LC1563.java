package com.leetcode.hard;

import java.util.Arrays;

public class LC1563 {

    public int stoneGameV(int[] stoneValue) {
        int[] pre = new int[stoneValue.length];
        pre[0] = stoneValue[0];
        for (int i = 1; i < stoneValue.length; i++) {
            pre[i] = pre[i - 1] + stoneValue[i];
        }
        int[][] dp = new int[stoneValue.length + 1][stoneValue.length + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(pre, 0, pre.length - 1, dp);
    }

    private int getSum(int[] arr, int start, int end) {
        if (start > 0) {
            return arr[end] - arr[start - 1];
        }
        return arr[end];
    }

    private int solve(int[] arr, int start, int end, int[][] dp) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        int best = 0;
        for (int i = start; i <= end; i++) {
            int left = getSum(arr, start, i);
            int right = getSum(arr, i + 1, end);
            if (left == right) {
                best = Math.max(Math.max(best, left + solve(arr, start, i, dp)), left + solve(arr, i + 1, end, dp));
            } else if (left < right) {
                best = Math.max(best, left + solve(arr, start, i, dp));
            } else {
                best = Math.max(best, right + solve(arr, i + 1, end, dp));
            }
        }
        return dp[start][end] = best;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4};
        LC1563 l = new LC1563();
        System.out.println(l.stoneGameV(arr));
    }
}
