package com.leetcode.random3;

public class SplitArray {

  public int splitArray(int[] nums, int m) {
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      pre[i] = pre[i - 1] + nums[i];
    }
    return split(new Integer[nums.length + 1][m + 1], 0, pre, m);

  }

  private int split(Integer[][] dp, int idx, int[] pre, int m) {
    if (dp[idx][m] != null) {
      return dp[idx][m];
    }
    if (m == 1) {
      return getSum(pre, idx, pre.length - 1);
    }
    int min = Integer.MAX_VALUE;
    for (int i = idx; i + 1 < pre.length; i++) {
      int left = getSum(pre, idx, i);
      int right = split(dp, i + 1, pre, m - 1);
      min = Math.min(min, Math.max(left, right));
    }
    dp[idx][m] = min;
    return min;
  }

  private int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    SplitArray s = new SplitArray();
    System.out.println(s.splitArray(new int[]{1, 2, 3, 4, 5}, 2));
  }

}
