package com.leetcode.random;

import java.util.Arrays;

public class BombEnemy {


  int max = Integer.MIN_VALUE;

  public int maxKilledEnemies(char[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int[][] rowArr = new int[grid.length][grid[0].length];
    int rows = grid.length;
    int cols = grid[0].length;
    for (int row = 0; row < rows; row++) {
      rowArr[row][0] = grid[row][0] == 'E' ? 1 : 0;
      for (int col = 1; col < cols; col++) {
        if (grid[row][col] == 'W') {
          rowArr[row][col] = 0;
        } else {
          rowArr[row][col] = rowArr[row][col - 1] + (grid[row][col] == 'E' ? 1 : 0);
        }
      }
      for (int col = cols - 2; col >= 0; col--) {
        if (grid[row][col] != 'W') {
          rowArr[row][col] = rowArr[row][col + 1] != 0 ? rowArr[row][col + 1] : rowArr[row][col];
        }
      }
    }

    int max = 0;
    int[][] colArr = new int[grid.length][grid[0].length];
    for (int col = 0; col < cols; col++) {
      colArr[0][col] = grid[0][col] == 'E' ? 1 : 0;
      for (int row = 1; row < rows; row++) {
        if (grid[row][col] == 'W') {
          colArr[row][col] = 0;
        } else {
          colArr[row][col] = colArr[row - 1][col] + (grid[row][col] == 'E' ? 1 : 0);
        }
      }
      for (int row = rows - 2; row >= 0; row--) {
        if (grid[row][col] != 'W') {
          colArr[row][col] = colArr[row + 1][col] != 0 ? colArr[row + 1][col] : colArr[row][col];
        }
        max = Math.max(colArr[row][col], max);
      }

    }
    if (rows == 1) {
      return Arrays.stream(rowArr[0]).max().getAsInt();
    }
    if (cols == 1) {
      return max;
    }
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] != 'W') {
          max = Math.max(max, colArr[i][j] + rowArr[i][j]);
        }
      }
    }
    return max;
  }


  public static void main(String args[]) {
    BombEnemy b = new BombEnemy();
    char[][] grid = new char[][]{
        {'0', 'E', '0', '0'},
        {'E', '0', 'W', 'E'},
        {'0', 'E', '0', '0'},
    };

    System.out.println(b.maxKilledEnemies(grid));
  }

}
