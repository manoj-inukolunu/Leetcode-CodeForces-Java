package com.leetcode.hard;

import java.util.Arrays;

public class LC741 {

    public int cherryPickup(int[][] grid) {
        int[][][] dp = new int[50][50][50];
        for (int[][] rows : dp) {
            for (int[] row : rows) {
                Arrays.fill(row, -1);
            }
        }
        return Math.max(solve(grid, 0, 0, 0, dp), 0);
    }

    private int solve(int[][] grid, int r1, int c1, int c2, int[][][] dp) {
        int r2 = r1 + c1 - c2;
        if (r1 >= grid.length || r2 >= grid.length || r2 < 0 || c1 >= grid[r1].length || c2 >= grid[r2].length || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE / 2;
        }
        if (r1 == grid.length - 1 && c1 == grid[r1].length - 1) {
            return grid[r1][c1];
        }
        if (dp[r1][c1][c2] != -1) {
            return dp[r1][c1][c2];
        }
        int pick = grid[r1][c1];
        if (c1 != c2) pick += grid[r2][c2];
        int max = Math.max(solve(grid, r1 + 1, c1, c2, dp), solve(grid, r1, c1 + 1, c2, dp));
        max = Math.max(max, solve(grid, r1 + 1, c1, c2 + 1, dp));
        max = Math.max(max, solve(grid, r1, c1 + 1, c2 + 1, dp));
        max += pick;
        return dp[r1][c1][c2] = max;
    }
}
