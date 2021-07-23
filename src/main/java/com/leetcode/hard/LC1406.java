package com.leetcode.hard;

import java.util.Arrays;

public class LC1406 {

    public String stoneGameIII(int[] stoneValue) {
        int[][] dp = new int[stoneValue.length + 1][2];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        int alice = go(stoneValue, 0, 0, dp);
        int bob = Arrays.stream(stoneValue).sum() - alice;
        if (alice == bob) {
            return "Tie";
        } else if (alice > bob) {
            return "Alice";
        } else {
            return "Bob";
        }
    }

    private int go(int[] arr, int player, int idx, int[][] dp) {
        if (idx >= arr.length) {
            return 0;
        }
        if (dp[idx][player] != Integer.MIN_VALUE) {
            return dp[player][idx];
        }
        int scoreAlice;
        if (player == 0) {
            scoreAlice = arr[idx] + go(arr, 1, idx + 1, dp);
            if (idx + 1 < arr.length) {
                scoreAlice = Math.max(scoreAlice, arr[idx] + arr[idx + 1] + go(arr, 1, idx + 2, dp));
            }
            if (idx + 2 < arr.length) {
                scoreAlice = Math.max(scoreAlice, arr[idx] + arr[idx + 1] + arr[idx + 2] + go(arr, 1, idx + 3, dp));
            }
        } else {
            scoreAlice = go(arr, 0, idx + 1, dp);
            if (idx + 1 < arr.length) {
                scoreAlice = Math.min(scoreAlice, go(arr, 0, idx + 2, dp));
            }
            if (idx + 2 < arr.length) {
                scoreAlice = Math.min(scoreAlice, go(arr, 0, idx + 3, dp));
            }
        }
        dp[idx][player] = scoreAlice;
        return scoreAlice;
    }

    public static void main(String args[]) {
        LC1406 l = new LC1406();
        int[] arr = new int[]{1, 2, 3, 7};
        System.out.println(l.stoneGameIII(arr));
    }
}
