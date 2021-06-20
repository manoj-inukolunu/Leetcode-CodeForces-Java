package com.leetcode.random4;

import java.util.Arrays;

public class Max3SubArray {

  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      pre[i] = pre[i - 1] + nums[i];
    }
    int[] left = new int[nums.length];
    int[] right = new int[nums.length];
    int max = getSum(pre, 0, k - 1);
    for (int i = k - 1; i < nums.length; i++) {
      int sum = getSum(pre, i - k + 1, i);
      if (sum > max) {
        left[i] = i - k + 1;
        max = sum;
      } else {
        left[i] = left[i - 1];
      }
    }
    max = getSum(pre, nums.length - k, nums.length - 1);
    for (int i = nums.length - k; i >= 0; i--) {
      int sum = getSum(pre, i, i + k - 1);
      if (sum >= max) {
        right[i] = i;
        max = sum;
      } else {
        right[i] = right[i + 1];
      }
    }
    int[] res = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    int sum = Integer.MIN_VALUE;
    for (int i = k; i < nums.length - k; i++) {
      int curr = getSum(pre, left[i - 1], left[i - 1] + k - 1) + getSum(pre, i, i + k - 1) + getSum(pre, right[i + k], right[i + k] + k - 1);
      if (curr > sum) {
        res = new int[]{left[i - 1], i, right[i + k]};
        sum = curr;
      }
    }
    return res;
  }

  private int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    Max3SubArray m = new Max3SubArray();
    int[] res = m.maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2);
    System.out.println(Arrays.toString(res));
  }
}
