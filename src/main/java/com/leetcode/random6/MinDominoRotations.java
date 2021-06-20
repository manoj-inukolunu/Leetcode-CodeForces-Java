package com.leetcode.random6;

public class MinDominoRotations {

  public int minDominoRotations(int[] A, int[] B) {
    int min = Integer.MAX_VALUE;
    for (int i = 1; i <= 6; i++) {
      min = Math.min(possible(A, B, i), Math.min(possible(B, A, i), min));
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private int possible(int[] a, int[] b, int val) {
    int count = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != val) {
        if (b[i] == val) {
          count++;
        } else {
          return Integer.MAX_VALUE;
        }
      }
    }
    return count == 0 ? Integer.MAX_VALUE : count;
  }

  public static void main(String args[]) {
    int[] A = new int[]{3, 5, 1, 2, 3};
    int[] B = new int[]{3, 6, 3, 3, 4};
    MinDominoRotations m = new MinDominoRotations();
    System.out.println(m.minDominoRotations(A, B));
  }
}
