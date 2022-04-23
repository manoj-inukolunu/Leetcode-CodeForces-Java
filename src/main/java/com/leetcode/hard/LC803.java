package com.leetcode.hard;

public class LC803 {

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] ans = new int[hits.length];
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]] = 2;
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                dfs(0, i, grid);
            }
        }
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            grid[hit[0]][hit[1]] = 1;
            ans[i] = (dfs(hit[0], hit[1], grid) - 1);
        }
        return ans;
    }

    private int dfs(int row, int col, int[][] grid) {
        grid[row][col] = 3;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int count = 1;
        for (int[] dir : dirs) {
            int nextR = row + dir[0];
            int nextC = col + dir[1];
            if (inside(nextR, nextC, grid) && grid[nextR][nextC] == 1) {
                count += dfs(nextR, nextC, grid);
            }
        }
        return count;
    }

    private boolean inside(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }
}






