package com.leetcode.random3;

import java.util.Arrays;

public class LargestSubArray {


  public int[] largestSubarray(int[] nums, int k) {
    int start = Integer.MIN_VALUE, idx = -1;
    for (int i = 0; i + k <= nums.length; i++) {
      if (nums[i] > start) {
        start = nums[i];
        idx = i;
      }
    }
    int[] ans = new int[k];
    for (int i = 0; i < k; i++) {
      ans[i] = nums[idx++];
    }
    return ans;
  }

  public static void main(String args[]) {
    LargestSubArray l = new LargestSubArray();
    int[] ret = l.largestSubarray(new int[]{1, 4, 5, 2, 3}, 1);
    System.out.println(Arrays.toString(ret));
  }

}
