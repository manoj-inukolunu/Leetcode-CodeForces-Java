package com.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author manoji on 4/24/20.
 */
public class MaxGold {

  public int getMaximumGold(int[][] grid) {

    int answer = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int pickUp = dfs(grid, i, j);
        if (pickUp > answer) {
          answer = pickUp;
        }
      }
    }

    return answer;

  }


  private int dfs(int[][] grid, int row, int column) {
    if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length) {
      return 0;
    }

    if (grid[row][column] != 0) {
      int pickUp = grid[row][column];
      grid[row][column] = 0;

      int v1 = 0, v2 = 0, v3 = 0, v4 = 0;
      if (column - 1 >= 0 && grid[row][column - 1] != 0) {
        v1 = dfs(grid, row, column - 1);
        grid[row][column] = pickUp;
      }

      if (column + 1 < grid[row].length && grid[row][column + 1] != 0) {
        v2 = dfs(grid, row, column + 1);
        grid[row][column] = pickUp;
      }

      if (row - 1 >= 0 && grid[row - 1][column] != 0) {
        v3 = dfs(grid, row - 1, column);
        grid[row][column] = pickUp;
      }

      if (row + 1 < grid.length && grid[row + 1][column] != 0) {
        v4 = dfs(grid, row + 1, column);
        grid[row][column] = pickUp;
      }

      return pickUp + Math.max(Math.max(v1, v2), Math.max(v3, v4));
    }
    return 0;
  }


  public int countServers(int[][] grid) {
    int ans = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1 && (isConnectedRow(grid, i) || isConnectedComponent(grid, j))) {
          ans++;
        }
      }
    }
    return ans;
  }

  private boolean isConnectedComponent(int[][] grid, int j) {
    for (int row = 0; row < grid.length; row++) {
      if (grid[row][j] == 1) {
        return true;
      }
    }
    return false;
  }

  private boolean isConnectedRow(int[][] grid, int i) {
    for (int col = 0; col < grid[i].length; col++) {
      if (grid[i][col] == 1) {
        return true;
      }
    }
    return false;
  }


  public static void main(String args[]) {
    MaxGold m = new MaxGold();

    int[][] grid = new int[][]{
        {1, 0, 7},
        {2, 0, 6},
        {3, 4, 5},
        {0, 3, 0},
        {9, 0, 20}
    };

    System.out.println(m.getMaximumGold(grid));
  }

}
