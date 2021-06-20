package com.leetcode.random1;

import java.util.Arrays;

public class PartitionArray {

  public int maxSumAfterPartitioning(int[] arr, int k) {
    int[][] dp = new int[arr.length][k];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return partition(arr, 0, k, dp);
  }

  private int partition(int[] arr, int start, int k, int[][] dp) {
    if (dp[start][k] != -1) {
      return dp[start][k];
    }
    int curr = 0;
    for (int i = start + 1; i <= start + k && i <= arr.length; i++) {
      int max = getMax(arr, start, i);
      curr = Math.max(max * (i - start) + partition(arr, i, k, dp), curr);
    }
    dp[start][k] = curr;
    return curr;
  }

  private int getMax(int[] arr, int start, int end) {
    int max = 0;
    for (int i = start; i < end; i++) {
      max = Math.max(arr[i], max);
    }
    return max;
  }

  public static void main(String args[]) {
    PartitionArray p = new PartitionArray();
    int[] arr = new int[]{1};
    System.out.println(p.maxSumAfterPartitioning(arr, 1));

  }

}
