package com.leetcode.random10.sixmonths.medium;

import java.util.HashSet;

/**
 * @author manoji on 7/25/20.
 */
public class ClosedIslands {

  class Pair {

    int x, y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private HashSet<Pair> top = new HashSet<>(), left = new HashSet<>(), right = new HashSet<>(), down = new HashSet<>();

  public int closedIsland(int[][] grid) {

    int count = 0;

    boolean[][] visited = new boolean[grid.length][];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 0 && !visited[i][j] && i != 0 && j != 0 && i != grid.length - 1 && j != grid[i].length - 1) {
          dfs(grid, i, j, visited);
          count += checkBoundary(grid);
          clearBoundary();
        }
      }
    }
    return count;
  }

  private void dfs(int[][] grid, int row, int col, boolean[][] visited) {
    if (!visited[row][col]) {
      visited[row][col] = true;
      if (row - 1 >= 0) {
        if (grid[row - 1][col] == 1) {
          top.add(new Pair(row - 1, col));
        } else if (!visited[row - 1][col]) {
          dfs(grid, row - 1, col, visited);
        }
      }

      if (row + 1 < grid.length) {
        if (grid[row + 1][col] == 1) {
          down.add(new Pair(row + 1, col));
        } else if (!visited[row + 1][col]) {
          dfs(grid, row + 1, col, visited);
        }
      }
      if (col - 1 >= 0) {
        if (grid[row][col - 1] == 1) {
          left.add(new Pair(row, col - 1));
        } else if (!visited[row][col - 1]) {
          dfs(grid, row, col - 1, visited);
        }
      }

      if (col + 1 < grid[row].length) {
        if (grid[row][col + 1] == 1) {
          right.add(new Pair(row, col + 1));
        } else if (!visited[row][col + 1]) {
          dfs(grid, row, col + 1, visited);
        }
      }
    }
  }

  private boolean inside(int row, int col, int[][] grid) {
    return row < grid.length && row >= 0 && col < grid[row].length && col >= 0;
  }


  private void clearBoundary() {
    top.clear();
    left.clear();
    right.clear();
    down.clear();
  }

  private int checkBoundary(int[][] grid) {
    for (Pair pair : top) {
      if (grid[pair.x + 1][pair.y] != 0) {
        return 0;
      }
    }
    for (Pair pair : left) {
      if (grid[pair.x][pair.y + 1] != 0) {
        return 0;
      }
    }

    for (Pair pair : right) {
      if (grid[pair.x][pair.y - 1] != 0) {
        return 0;
      }
    }

    for (Pair pair : down) {
      if (grid[pair.x - 1][pair.y] != 0) {
        return 0;
      }
    }
    return 1;
  }


}
