package com.leetcode.random;

import java.util.Arrays;

public class MatBlock {

  public int[][] matrixBlockSum(int[][] mat, int K) {

    int[][] dp = new int[mat.length][mat[0].length];
    for (int i = 0; i < mat.length; i++) {
      int[] arr = dp[i];
      int[] curr = mat[i];
      arr[0] = curr[0];
      for (int j = 1; j < dp.length; j++) {
        arr[j] = curr[j] + arr[j - 1];
      }
    }
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {

        int sum = 0;
        for (int t = i - K; t <= i + K; t++) {
          if (t >= 0 && t < mat.length) {
            sum += getSum(dp[t], j - K, j + K);
          }
        }
        mat[i][j] = sum;
      }
    }
    return mat;
  }

  private int getSum(int[] arr, int start, int end) {
    if (start < 0) {
      start = 0;
    }
    if (end >= arr.length) {
      end = arr.length - 1;
    }
    if (start > 0) {
      return arr[end] - arr[start - 1];
    }
    return arr[end];
  }

  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
    };
    MatBlock m = new MatBlock();
    arr = m.matrixBlockSum(arr, 2);
    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }

  }

}
