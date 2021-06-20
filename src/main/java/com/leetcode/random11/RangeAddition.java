package com.leetcode.random11;

public class RangeAddition {

  public int[] getModifiedArray(int length, int[][] updates) {
    int[] arr = new int[length];
    for (int i = 0; i < updates.length; i++) {
      int start = updates[i][0];
      int end = updates[i][1];
      int inc = updates[i][2];
      if (end + 1 < arr.length) {
        arr[start] += inc;
        arr[end + 1] += (-inc);
      }
    }
    for (int i = 1; i < arr.length; i++) {
      arr[i] = arr[i - 1] + arr[i];
    }
    return arr;
  }

}
