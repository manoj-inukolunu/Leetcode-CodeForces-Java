package com.leetcode.contest;

import java.util.Arrays;

public class LexLargest {

  public int[] constructDistancedSequence(int n) {

    boolean[] used = new boolean[n + 1];
    int[] arr = new int[2 * n - 1];
    backtrack(arr, 0, used, n);
    return arr;
  }

  private boolean backtrack(int[] arr, int idx, boolean[] used, int total) {
    if (idx >= arr.length) {
      return true;
    }
    if (arr[idx] != 0) {
      return backtrack(arr, idx + 1, used, total);
    }
    for (int i = total; i >= 1; i--) {
      if (used[i]) {
        continue;
      }
      used[i] = true;
      arr[idx] = i;
      if (i == 1) {
        if (backtrack(arr, idx + 1, used, total)) {
          return true;
        }
      } else if (idx + i < arr.length && arr[idx + i] == 0) {
        arr[idx + i] = i;
        if (backtrack(arr, idx + 1, used, total)) {
          return true;
        }
        arr[idx + i] = 0;
      }
      arr[idx] = 0;
      used[i] = false;
    }
    return false;
  }


  public static void main(String args[]) {
    LexLargest l = new LexLargest();
    System.out.println(Arrays.toString(l.constructDistancedSequence(3)));
  }

}
