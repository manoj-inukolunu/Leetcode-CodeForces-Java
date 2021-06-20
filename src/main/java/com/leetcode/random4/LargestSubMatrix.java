package com.leetcode.random4;

import java.util.Arrays;

public class LargestSubMatrix {

  public int largestSubmatrix(int[][] matrix) {
    for (int col = 0; col < matrix[0].length; col++) {
      for (int row = 0; row < matrix.length; row++) {
        if (row - 1 >= 0) {
          if (matrix[row - 1][col] != 0) {
            matrix[row][col] = (matrix[row][col] == 1 ? matrix[row - 1][col] + 1 : 0);
          }
        }
      }
    }
    int max = Integer.MIN_VALUE;
    for (int row = 0; row < matrix.length; row++) {
      int[] curr = matrix[row];
      Arrays.sort(curr);
      for (int i = curr.length - 1; i >= 0; i--) {
        max = Math.max(max, Math.max(curr[i], curr[i] * (curr.length - i)));
      }
    }
    return max;
  }

  public static void main(String args[]) {
    int[][] mat = new int[][]{
        {0, 0, 1},
        {1, 1, 1},
        {1, 0, 1}
    };
    LargestSubMatrix l = new LargestSubMatrix();
    System.out.println(l.largestSubmatrix(mat));
  }


}
