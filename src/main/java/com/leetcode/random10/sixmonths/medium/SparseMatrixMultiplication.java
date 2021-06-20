package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

public class SparseMatrixMultiplication {

  public int[][] multiply(int[][] A, int[][] B) {

    int[][] res = new int[A.length][B[0].length];

    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < B.length; i++) {

    }
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[i].length; j++) {
        res[i][j] = 0;
        for (int k = 0; k < B.length; k++) {
          res[i][j] += A[i][k] * B[k][j];
        }
      }
    }

    return res;
  }

}
