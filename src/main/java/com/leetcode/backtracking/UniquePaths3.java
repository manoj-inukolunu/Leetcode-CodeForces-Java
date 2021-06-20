package com.leetcode.backtracking;

import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * @author manoji on 7/13/20.
 */
public class UniquePaths3 {


  private int ans = 0;
  int numObstacles = 0;

  public int uniquePathsIII(int[][] grid) {

    int startRow = 0, startColumn = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == -1) {
          numObstacles++;
        }
        if (grid[i][j] == 1) {
          startRow = i;
          startColumn = j;
        }
      }
    }
    dfs(grid, startRow, startColumn, 1);
    return ans;
  }


  private void dfs(int[][] grid, int row, int col, int count) {
    if (count == (grid.length * grid[0].length) - numObstacles && grid[row][col] == 2) {
      ans++;
      return;
    } else if (grid[row][col] == 2) {
      return;
    }
    if (count == (grid.length * grid[0].length)) {
      return;
    }
    int curr = grid[row][col];
    if (row + 1 < grid.length && grid[row + 1][col] != -1) {
      grid[row][col] = -1;
      dfs(grid, row + 1, col, count + 1);
      grid[row][col] = curr;
    }

    if (col + 1 < grid[0].length && grid[row][col + 1] != -1) {
      grid[row][col] = -1;
      dfs(grid, row, col + 1, count + 1);
      grid[row][col] = curr;
    }

    if (row - 1 >= 0 && grid[row - 1][col] != -1) {
      grid[row][col] = -1;
      dfs(grid, row - 1, col, count + 1);
      grid[row][col] = curr;
    }

    if (col - 1 >= 0 && grid[row][col - 1] != -1) {
      grid[row][col] = -1;
      dfs(grid, row, col - 1, count + 1);
      grid[row][col] = curr;
    }
  }


  public static void main(String args[]) throws Exception {
    List<String> lines = FileUtils.readLines(new File("/Users/manoji/fbint"));

    int count = 0;
    for (String line : lines) {
      if (!Character.isDigit(line.charAt(0))) {
        System.out.println(line);
        count++;
      }
    }

    System.out.println(count);
  }

}
