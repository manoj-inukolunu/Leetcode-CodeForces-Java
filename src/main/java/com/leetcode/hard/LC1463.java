package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;

public class LC1463 {

    public int cherryPickup(int[][] grid) {
        int[][][] dp = new int[grid.length + 1][grid[0].length + 1][grid[0].length + 1];
        for (int[][] d : dp) {
            for (int[] row : d) {
                Arrays.fill(row, -1);
            }
        }
        return solve(grid, 0, 0, grid[0].length - 1, dp);
    }

    private int solve(int[][] grid, int row, int r1Col, int r2Col, int[][][] dp) {
        if (row < 0 || row >= grid.length || r1Col < 0 || r1Col >= grid[row].length || r2Col < 0 || r2Col >= grid[row].length) {
            return 0;
        }
        if (dp[row][r1Col][r2Col] != -1) {
            return dp[row][r1Col][r2Col];
        }
        int max = 0;
        if (r1Col == r2Col) {
            int[] cols = {-1, 0, 1};
            for (int j : cols) {
                for (int col : cols) {
                    max = Math.max(max, grid[row][r1Col] + solve(grid, row + 1, r1Col + j, r2Col + col, dp));
                }
            }
        } else {
            int[] cols = {-1, 0, 1};
            for (int j : cols) {
                for (int col : cols) {
                    max = Math.max(max, grid[row][r1Col] + grid[row][r2Col] + solve(grid, row + 1, r1Col + j,
                            r2Col + col, dp));
                }
            }
        }
        return dp[row][r1Col][r2Col] = max;
    }

    public static void main(String[] args) {
        int[][] grid = Utils.convertToTwoDIntArray("[[1,1],[1,1]]");
        LC1463 l = new LC1463();
        System.out.println(l.cherryPickup(grid));
    }
}
