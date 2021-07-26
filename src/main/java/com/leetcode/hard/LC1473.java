package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;

public class LC1473 {


    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < houses.length; i++) {
            houses[i]--;
        }
        int[][][] dp = new int[houses.length + 1][n + 1][target + 1];
        for (int[][] ch : dp) {
            for (int[] row : ch) {
                Arrays.fill(row, -1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < cost[0].length; i++) {
            if (houses[0] == -1) {
                min = Math.min(min, cost[0][i] + solve(houses, cost, 1, i, 1, target, dp));
            } else {
                min = Math.min(min, solve(houses, cost, 1, houses[0], 1, target, dp));
            }
        }
        //System.out.println(min);
        return (min >= Integer.MAX_VALUE / 2) ? -1 : min;
    }

    private int solve(int[] houses, int[][] cost, int idx, int prevHouseColor, int numGroups, int target, int[][][] dp) {
        if (idx >= houses.length) {
            return numGroups == target ? 0 : Integer.MAX_VALUE / 2;
        }
        if (numGroups > target) {
            return Integer.MAX_VALUE / 2;
        }
        if (dp[idx][prevHouseColor][numGroups] != -1) {
            return dp[idx][prevHouseColor][numGroups];
        }
        int minCost;
        if (houses[idx] == -1) {
            minCost = cost[idx][prevHouseColor] + solve(houses, cost, idx + 1, prevHouseColor, numGroups, target, dp);
            for (int i = 0; i < cost[idx].length; i++) {
                if (i != prevHouseColor) {
                    minCost = Math.min(minCost, cost[idx][i] + solve(houses, cost, idx + 1, i, numGroups + 1, target, dp));
                }
            }
        } else if (houses[idx] == prevHouseColor) {
            minCost = solve(houses, cost, idx + 1, prevHouseColor, numGroups, target, dp);
        } else {
            minCost = solve(houses, cost, idx + 1, houses[idx], numGroups + 1, target, dp);
        }
        dp[idx][prevHouseColor][numGroups] = minCost;
        return minCost;
    }


    public static void main(String args[]) {
        int[] houses = new int[]{0, 2, 1, 2, 0};
        int[][] cost = Utils.convertToTwoDIntArray("[[1,10],[10,1],[10,1],[1,10],[5,1]]");
        LC1473 l = new LC1473();
        System.out.println(l.minCost(houses, cost, 5, 2, 3));
    }
}
