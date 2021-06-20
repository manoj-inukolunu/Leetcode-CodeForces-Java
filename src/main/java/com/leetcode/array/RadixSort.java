package com.leetcode.array;

import java.util.Arrays;

/**
 * @author manoji on 7/11/20.
 */
public class RadixSort {


  private int getMax(int arr[]) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(arr[i], max);
    }
    return max;
  }

  private void countSort(int arr[], int exp) {
    int out[] = new int[arr.length];
    int count[] = new int[10];

    Arrays.fill(count, 0);
  }

}
