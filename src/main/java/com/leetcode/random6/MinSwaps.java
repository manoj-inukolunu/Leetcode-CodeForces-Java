package com.leetcode.random6;

public class MinSwaps {

  public int minSwaps(int[] data) {

    int count = 0, min;
    for (int i = 0; i < data.length; i++) {
      if (data[i] == 1) {
        count++;
      }
    }
    int zeroes = 0;
    for (int i = 0; i < count; i++) {
      if (data[i] == 0) {
        zeroes++;
      }
    }
    if (zeroes == 0) {
      return 0;
    }
    min = zeroes;
    for (int i = 1; i + count <= data.length; i++) {
      int prev = data[i - 1];
      int next = data[i + count - 1];
      if (prev == 0) {
        zeroes--;
      }
      if (next == 0) {
        zeroes++;
      }
      min = Math.min(zeroes, min);
    }
    return min;
  }

  public static void main(String args[]) {
    MinSwaps m = new MinSwaps();
    int[] arr = new int[]{1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1};
    System.out.println(m.minSwaps(arr));
  }

}
