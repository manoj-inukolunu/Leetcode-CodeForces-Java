package com.leetcode.random3;

import java.util.Arrays;

public class BallFall {

  public int[] findBall(int[][] grid) {

    int[] arr = new int[grid[0].length];
    int[][][] dp = new int[grid.length + 1][grid.length][3];
    for (int[][] row : dp) {
      for (int[] r : row) {
        Arrays.fill(r, -1);
      }
    }
    for (int i = 0; i < grid[0].length; i++) {
      arr[i] = dfs(0, i, 0, grid, dp);
    }
    return arr;
  }

  boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  private int dfs(int row, int col, int dir, int[][] grid, int[][][] dp) {
    if (row == grid.length) {
      return col;
    }

    if (dir == 0) {
      if (inside(row, col + 1, grid) && grid[row][col + 1] == 1 && grid[row][col] == 1) {
        return dfs(row, col + 1, 1, grid, dp);
      }
      if (inside(row, col - 1, grid) && grid[row][col - 1] == -1 && grid[row][col] == -1) {
        return dfs(row, col - 1, 2, grid, dp);
      }
    } else if (dir == 1) {
      if (grid[row][col] == 1) {
        return dfs(row + 1, col, 0, grid, dp);
      }
    } else if (dir == 2) {
      if (grid[row][col] == -1) {
        return dfs(row + 1, col, 0, grid, dp);
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    BallFall b = new BallFall();
    int[][] grid = new int[][]{
        {-1, 1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 1, 1, -1, -1, -1, 1, 1, 1, -1, -1, 1, 1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1,
            -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, 1, 1, -1, 1, 1, 1,
            -1, -1, -1, 1, -1, 1, -1, -1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, -1, -1}};
    int[] res = b.findBall(grid);
    System.out.println(Arrays.toString(res));
  }

}
