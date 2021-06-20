package com.leetcode.random5;

public class MaxSubArrayOneOp {

  public int maxSumAfterOperation(int[] nums) {
    int[][] dp = new int[nums.length][2];
    dp[0][0] = nums[0];
    dp[0][1] = nums[0] * nums[0];

    for (int i = 1; i < nums.length; i++) {
      dp[i][0] = Math.max(nums[i], dp[i - 1][0] + nums[i]);
      dp[i][1] = Math.max(nums[i] * nums[i], Math.max(dp[i - 1][1] + nums[i], dp[i - 1][0] + (nums[i] * nums[i])));
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < dp.length; i++) {
      max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
    }

    return max;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, -1, 1, 1, -1, -1, 1};
    MaxSubArrayOneOp m = new MaxSubArrayOneOp();
    System.out.println(m.maxSumAfterOperation(arr));
  }

}
