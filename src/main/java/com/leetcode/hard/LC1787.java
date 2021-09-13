package com.leetcode.hard;

import java.util.Arrays;

public class LC1787 {


    int[] vals = new int[3000];
    int[][] freq = new int[3000][3000];

    public int minChanges(int[] nums, int k) {
        int[][] dp = new int[3000][3000];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int i = 0; i < nums.length; i++) {
            freq[i % k][nums[i]]++;
        }
        for (int i = 0; i < freq.length; i++) {
            int total = 0;
            for (int j = 0; j < freq[i].length; j++) {
                total += freq[i][j];
            }
            vals[i] = total;
        }
        return solve(nums, freq, 0, 0, k, dp);
    }

    private int solve(int[] nums, int[][] freq, int idx, int xor, int k,
                      int[][] dp) {
        if (idx >= k) {
            return xor == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (dp[idx][xor] != -1) {
            return dp[idx][xor];
        }
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < 1024; i++) {
            int change = solve(nums, freq, idx + 1, xor ^ i, k, dp);
            if (change != Integer.MAX_VALUE) {
                best = Math.min(best, change + getChange(freq, idx, i, k));
            }
        }
        return dp[idx][xor] = best;
    }

    int getChange(int[][] freq, int idx, int num, int k) {
        return vals[idx % k] - freq[idx % k][num];
    }


    public static void main(String[] args) {
        LC1787 l = new LC1787();
        int[] arr = new int[]{1, 2, 4, 1, 2, 5, 1, 2, 6};
        System.out.println(l.minChanges(arr, 3));
    }
}
