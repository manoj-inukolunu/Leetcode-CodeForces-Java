package com.leetcode.random3;

import java.util.HashMap;
import java.util.HashSet;

public class LargeIsland {

  private boolean inside(int row, int col, int[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  int area = 0;

  public int largestIsland(int[][] grid) {
    HashMap<Integer, Integer> aMap = new HashMap<>();
    int maxArea = 0;
    int id = 2;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          dfs(i, j, grid, id);
          aMap.put(id, area);
          maxArea = Math.max(area, maxArea);
          area = 0;
          id++;
        }
      }
    }
    /*for (int[] row : grid) {
      System.out.println(Arrays.toString(row));
    }*/
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j] == 0) {
          int curr = 0;
          int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
          HashSet<Integer> seen = new HashSet<>();
          for (int[] dir : dirs) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            if (inside(row, col, grid) && !seen.contains(grid[row][col])) {
              curr += aMap.get(grid[row][col]);
              seen.add(grid[row][col]);
            }
          }
          max = Math.max(curr + 1, max);
        }
      }
    }
    return max == Integer.MIN_VALUE ? maxArea : max;
  }

  private void dfs(int row, int col, int[][] grid, int id) {
    if (grid[row][col] == 1) {
      area++;
      grid[row][col] = id;
      int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
      for (int[] dir : dirs) {
        int nextR = dir[0] + row;
        int nextC = dir[1] + col;
        if (inside(nextR, nextC, grid)) {
          dfs(nextR, nextC, grid, id);
        }
      }
    }
  }

  public static void main(String args[]) {
    LargeIsland l = new LargeIsland();
    int[][] arr = new int[][]{
        {1, 1},
        {1, 1}
    };
    System.out.println(l.largestIsland(arr));
  }

}
