package com.leetcode.hard;

import com.leetcode.common.Utils;

import java.util.Arrays;

public class LC174 {


    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(dungeon, 0, 0, dp);
    }

    private int solve(int[][] grid, int row, int col, int[][] dp) {
        if (row == grid.length - 1 && col == grid[row].length - 1) {
            return (grid[row][col] <= 0) ? -grid[row][col] + 1 : 1;
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int best = Integer.MAX_VALUE;
        if (inside(row + 1, col, grid)) {
            best = Math.min(best, solve(grid, row + 1, col, dp));
        }
        if (inside(row, col + 1, grid)) {
            best = Math.min(best, solve(grid, row, col + 1, dp));
        }
        best -= grid[row][col];
        return dp[row][col] = (best <= 0 ? 1 : best);

    }


    boolean inside(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }

    public static void main(String[] args) {
        LC174 l = new LC174();
        int[][] grid = Utils.convertToTwoDIntArray("[[-2,-3,3],[-5,-10,1],[10,30,-5]]");
        int[][] grid1 = Utils.convertToTwoDIntArray("[[0]]");
        System.out.println(l.calculateMinimumHP(grid1));
    }
}
