package com.leetcode.random;

import java.util.Arrays;

public class PrevPermutation {

  public int[] prevPermOpt1(int[] A) {
    boolean increasing = true;
    for (int i = 0; i < A.length; i++) {
      if (i >= 1 && A[i - 1] > A[i]) {
        increasing = false;
      }
    }
    if (increasing) {
      return A;
    }
    for (int i = A.length - 1; i >= 0; i--) {
      int indexToSwapWith = -1, max = Integer.MIN_VALUE;
      for (int j = i + 1; j < A.length; j++) {
        if (A[j] < A[i]) {
          if (A[j] > max) {
            max = A[j];
            indexToSwapWith = j;
          }
        }
      }
      if (indexToSwapWith != -1) {
        int temp = A[i];
        A[i] = A[indexToSwapWith];
        A[indexToSwapWith] = temp;
        break;
      }
    }
    return A;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{3, 1, 1, 3};
    PrevPermutation p = new PrevPermutation();
    arr = p.prevPermOpt1(arr);
    System.out.println(Arrays.toString(arr));
  }

}
