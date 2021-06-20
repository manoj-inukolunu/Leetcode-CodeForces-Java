package com.leetcode.random;

import java.util.Arrays;

public class SmallestCommonElement {

  public int smallestCommonElement(int[][] mat) {
    int[] row = mat[0];
    for (int i = 0; i < row.length; i++) {
      boolean found = true;
      for (int j = 1; j < mat.length; j++) {
        int val = Arrays.binarySearch(mat[j], row[i]);
        if (val == -1) {
          found = false;
          break;
        }
      }
      if (found) {
        return row[i];
      }
      found = true;
    }
    return -1;
  }

}
