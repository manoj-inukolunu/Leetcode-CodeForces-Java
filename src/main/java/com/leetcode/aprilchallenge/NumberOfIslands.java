package com.leetcode.aprilchallenge;

/**
 * @author manoji on 4/17/20.
 */
public class NumberOfIslands {


  public int maxAreaOfIsland(int[][] grid) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          int area = dfs(grid, i, j, 1);
          if (area > max) {
            max = area;
          }
        }
      }
    }
    return max;
  }

  private int dfs(int[][] grid, int i, int j, int area) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
      return area;
    }
    grid[i][j] = 0;
    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
      area = dfs(grid, i - 1, j, area + 1);
    }

    if (i + 1 < grid.length && grid[i + 1][j] == 1) {
      area = dfs(grid, i + 1, j, area + 1);
    }

    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
      area = dfs(grid, i, j - 1, area + 1);
    }

    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
      area = dfs(grid, i, j + 1, area + 1);
    }
    return area;
  }

  public int numIslands(char[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
      return;
    }
    grid[i][j] = '2';
    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
      dfs(grid, i - 1, j);
    }

    if (i + 1 < grid.length && grid[i + 1][j] == '1') {
      dfs(grid, i + 1, j);
    }

    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
      dfs(grid, i, j - 1);
    }

    if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
      dfs(grid, i, j + 1);
    }
  }

  public static void main(String args[]) {
    char[][] islands = new char[][]{
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'},
    };

    int[][] isl = new int[][]{
        {1, 1, 0, 0, 0},
        {1, 1, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 1, 1},
    };

    NumberOfIslands num = new NumberOfIslands();

    System.out.println(num.maxAreaOfIsland(isl));
  }

}
