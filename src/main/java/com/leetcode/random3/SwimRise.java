package com.leetcode.random3;

public class SwimRise {

  public int swimInWater(int[][] grid) {
    int max = -1, min = Integer.MAX_VALUE;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        max = Math.max(grid[i][j], max);
        min = Math.min(grid[i][j], min);
      }
    }
    int low = min, high = max, ans = 0;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      boolean[][] vis = new boolean[grid.length][grid.length];
      if (canSwim(0, 0, grid, mid, vis)) {
        high = mid - 1;
        ans = mid;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }

  private boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  private boolean canSwim(int row, int col, int[][] grid, int time, boolean[][] visited) {
    if (row == grid.length - 1 && col == grid[row].length - 1) {
      return true;
    }
    visited[row][col] = true;
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    boolean swim = false;
    for (int dir[] : dirs) {
      int nextR = dir[0] + row;
      int nextC = dir[1] + col;
      if (inside(nextR, nextC, grid) && time >= grid[row][col] && time >= grid[nextR][nextC] && !visited[nextR][nextC]) {
        swim = swim || canSwim(nextR, nextC, grid, time, visited);
      }
    }
    return swim;
  }

}
