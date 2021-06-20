/*
package com.leetcode.random7;

public class MaxAbsVal {

  public int maxAbsValExpr(int[] arr1, int[] arr2) {
    int[][] a1 = new int[arr1.length][2];
    int[][] a2 = new int[arr2.length][2];
    a1[arr1.length - 1][0] = arr1[arr1.length - 1];
    a1[arr1.length - 1][1] = arr1[arr1.length - 1];
    for (int i = arr1.length - 2; i >= 0; i--) {
      a1[i][0] = Math.max(arr1[i + 1], arr1[i]);
      a1[i][1] = Math.min(arr1[i + 1], arr1[i]);
    }
    a2[arr2.length - 1][0] = arr2[arr2.length - 1];
    a2[arr2.length - 1][1] = arr2[arr2.length - 1];
    for (int i = arr2.length - 2; i >= 0; i--) {
      a2[i][0] = Math.max(arr2[i + 1], arr2[i]);
      a2[i][1] = Math.min(arr2[i + 1], arr2[i]);
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr1.length; i++) {
      max = Math.max()
    }

    return max;
  }

}
*/
