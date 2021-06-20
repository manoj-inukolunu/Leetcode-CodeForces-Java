package com.leetcode.random;

public class Wiggle {


  public int wiggleMaxLength(int[] nums) {

    int[][] dp = new int[nums.length][2];
    int ans = 0;
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = dp[i][1] = 1;
    }
    for (int i = 0; i < nums.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (nums[i] < nums[j]) {
          dp[i][1] = Math.max(dp[i][1], 1 + dp[j][0]);
          ans = Math.max(ans, dp[i][1]);
        } else if (nums[i] > nums[j]) {
          dp[i][0] = Math.max(dp[i][0], 1 + dp[j][1]);
          ans = Math.max(ans, dp[i][0]);
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    Wiggle w = new Wiggle();
    System.out.println(w.wiggleMaxLength(arr));
  }

}
