package com.leetcode.random10.sixmonths.medium;

public class ClosedIslands2 {


  public int closedIsland(int[][] grid) {

    int count = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 0 && dfs(grid, i, j)) {
          System.out.println(i + " " + j);
          count++;
        }
      }
    }
    return count;
  }

  private boolean inside(int[][] grid, int row, int col) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

  private boolean isBoundary(int[][] grid, int row, int col) {
    return row == 0 || row == grid.length - 1 || col == 0 || col == grid[row].length;
  }

  private boolean dfs(int[][] grid, int row, int col) {

    if (grid[row][col] == 0) {

      grid[row][col] = 1;
      boolean left = inside(grid, row, col - 1) && dfs(grid, row, col - 1);
      boolean right = inside(grid, row, col + 1) && dfs(grid, row, col + 1);
      boolean top = inside(grid, row - 1, col) && dfs(grid, row - 1, col);
      boolean bottom = inside(grid, row + 1, col) && dfs(grid, row + 1, col);
      if (isBoundary(grid, row, col)) {
        return false;
      }
      return left && right && top && bottom;
    }

    return true;
  }

  public static void main(String args[]) {
    ClosedIslands2 c = new ClosedIslands2();

    int[][] arr = new int[][]{
        {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
        {1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
        {1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
        {1, 0, 1, 0, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
        {1, 1, 0, 0, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
        {1, 1, 0, 1, 0, 1, 0, 0, 1, 0}
    };

    System.out.println(c.closedIsland(arr));
  }

}
