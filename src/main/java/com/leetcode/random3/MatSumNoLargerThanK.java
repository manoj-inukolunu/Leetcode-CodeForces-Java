package com.leetcode.random3;

public class MatSumNoLargerThanK {

  public int maxSumSubmatrix(int[][] matrix, int sum) {
    int[][] pre = new int[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      pre[i][0] = matrix[i][0];
      for (int j = 1; j < matrix[i].length; j++) {
        pre[i][j] = pre[i][j - 1] + matrix[i][j];
      }
    }
    for (int col = 0; col < matrix[0].length; col++) {
      for (int row = 1; row < matrix.length; row++) {
        pre[row][col] += pre[row - 1][col];
      }
    }
    int ans = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        for (int t = i; t < matrix.length; t++) {
          for (int k = j; k < matrix[i].length; k++) {
            int cur = getSum(pre, i, j, t, k);
            if (cur <= sum) {
              ans = Math.max(ans, sum);
            }
          }
        }
      }
    }
    return sum;
  }

  int getSum(int aux[][], int row1, int col1, int row2, int col2) {
    int res = aux[row2][col2];
    if (row1 > 0) {
      res = res - aux[row1 - 1][col2];
    }
    if (col1 > 0) {
      res = res - aux[row2][col1 - 1];
    }
    if (row1 > 0 && col1 > 0) {
      res = res + aux[row1 - 1][col1 - 1];
    }
    return res;
  }

  public static void main(String args[]) {
    MatSumNoLargerThanK m = new MatSumNoLargerThanK();
    int[][] arr = new int[][]{
        {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
    };
    m.maxSumSubmatrix(arr, 1);
  }

}
