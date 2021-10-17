package com.leetcode.hard;

import java.util.Arrays;

public class LC768 {

    public int maxChunksToSorted(int[] arr) {
        int[][] largest = new int[arr.length][arr.length];
        int[][] smallest = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                largest[i][j] = arr[j];
                smallest[i][j] = arr[j];
                if (j > i) {
                    largest[i][j] = Math.max(largest[i][j - 1], largest[i][j]);
                    smallest[i][j] = Math.min(smallest[i][j - 1], smallest[i][j]);
                }
            }
        }
        int[][] dp = new int[arr.length + 1][arr.length + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int ans = solve(arr, largest, smallest, 0, arr.length - 1, dp);
        return ans == Integer.MIN_VALUE ? 1 : ans;
    }


    private int solve(int[] arr, int[][] largest, int[][] smallest, int start, int end, int[][] dp) {
        if (start == end) {
            return 1;
        }
        if (end - start == 1) {
            return arr[start] <= arr[end] ? 2 : 1;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        int best = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (largest[start][i] <= smallest[i + 1][end]) {
                int left = solve(arr, largest, smallest, start, i, dp);
                left = (left == Integer.MIN_VALUE ? 1 : left);
                int right = solve(arr, largest, smallest, i + 1, end, dp);
                right = (right == Integer.MIN_VALUE ? 1 : right);
                best = Math.max(best, left + right);
            }
        }

        return dp[start][end] = best;
    }

    public static void main(String[] args) {
        LC768 l = new LC768();
        int[] nums = new int[]{0, 3, 0, 3, 2};
        System.out.println(l.maxChunksToSorted(nums));
    }
}
