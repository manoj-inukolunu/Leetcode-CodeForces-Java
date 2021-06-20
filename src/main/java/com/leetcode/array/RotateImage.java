package com.leetcode.array;

import java.util.Arrays;

/**
 * @author manoji on 5/7/20.
 */
public class RotateImage {

  public void rotate(int[][] matrix) {
    int rows = matrix.length - 1;
    int cols = matrix.length - 1;
    for (int i = 0; i <= rows / 2; i++) {
      rotate(matrix, i, i, cols - i);
    }
  }


  private void rotate(int[][] matrix, int row, int start, int end) {
    for (int i = start; i < end; i++) {
      dfs(matrix, row, i, row, i, matrix[row][i]);
    }
  }

  private void dfs(int[][] matrix, int row, int col, int finalRow, int finalCol, int current) {
    int nextRow = col;
    int nextCol = matrix.length - 1 - row;
    if (nextRow == finalRow && nextCol == finalCol) {
      matrix[nextRow][nextCol] = current;
      return;
    }
    int next = matrix[nextRow][nextCol];
    matrix[nextRow][nextCol] = current;
    dfs(matrix, nextRow, nextCol, finalRow, finalCol, next);
  }


  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {5, 1, 9, 11},
        {2, 4, 8, 10},
        {13, 3, 6, 7},
        {15, 14, 12, 16}
    };

    int[][] threeArr = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    RotateImage r = new RotateImage();

    r.rotate(threeArr);
    for (int[] row : threeArr) {
      System.out.println(Arrays.toString(row));
    }
  }


}
