package com.leetcode.Tree.medium;

import java.util.Arrays;

public class NumEnclaves {

  private int area = 0;

  public int numEnclaves(int[][] A) {

    for (int row = 0; row < A.length; row++) {
      if (A[row][0] == 1) {
        dfs(row, 0, A);
      }
      if (A[row][A[0].length - 1] == 1) {
        dfs(row, A[0].length - 1, A);
      }
    }
    for (int col = 0; col < A[0].length; col++) {
      if (A[0][col] == 1) {
        dfs(0, col, A);
      }
      if (A[A.length - 1][col] == 1) {
        dfs(A.length - 1, col, A);
      }
    }

    for (int[] row : A) {
      System.out.println(Arrays.toString(row));
    }
    area = 0;
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A[i].length; j++) {
        if (A[i][j] == 1) {
          dfs(i, j, A);
        }
      }
    }
    return area;
  }

  private boolean inside(int row, int col, int[][] A) {
    return row >= 0 && col >= 0 && row < A.length && col < A[row].length;
  }

  private void dfs(int row, int col, int[][] A) {
    if (inside(row, col, A) && A[row][col] == 1) {
      area++;
      A[row][col] = 0;
      dfs(row + 1, col, A);
      dfs(row - 1, col, A);
      dfs(row, col + 1, A);
      dfs(row, col - 1, A);
    }
  }

  public static void main(String args[]) {
    NumEnclaves n = new NumEnclaves();
    int arr[][] = new int[][]{{0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0},
        {1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0},
        {0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0}, {1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0}, {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1}};
    System.out.println(n.numEnclaves(arr));
  }


}
