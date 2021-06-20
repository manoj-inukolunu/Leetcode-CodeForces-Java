package com.leetcode.random3;

public class BurstBaloons {

  public int maxCoins(int[] nums) {
    return burst(nums, 0, nums.length - 1, new Integer[nums.length + 1][nums.length + 1]);
  }

  private int burst(int[] arr, int start, int end, Integer[][] dp) {
    if (start > end) {
      return 0;
    }
    if (dp[start][end] != null) {
      return dp[start][end];
    }
    int max = Integer.MIN_VALUE;
    for (int i = start; i <= end; i++) {
      int prev = 1, next = 1;
      if (start != 0) {
        prev = arr[start - 1];
      }
      if (end != arr.length - 1) {
        next = arr[end + 1];
      }
      int curr = arr[i] * prev * next;
      int left = burst(arr, start, i - 1, dp);
      int right = burst(arr, i + 1, end, dp);
      max = Math.max(max, curr + left + right);
    }
    dp[start][end] = max;
    return max;
  }

  public static void main(String args[]) {
    BurstBaloons b = new BurstBaloons();
    int[] arr = new int[]{3, 1, 5, 8};
    System.out.println(b.maxCoins(arr));
  }


}
