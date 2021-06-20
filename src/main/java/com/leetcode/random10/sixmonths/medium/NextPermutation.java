package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class NextPermutation {

  public void nextPermutation(int[] nums) {
    int k = nums.length - 2;
    while (k >= 0 && nums[k] >= nums[k + 1]) {
      k--;
    }
    if (k == -1) {
      return;
    }
    for (int i = nums.length - 1; i > k; i--) {
      if (nums[i] > nums[k]) {
        int temp = nums[k];
        nums[k] = nums[i];
        nums[i] = temp;
        break;
      }
    }
    Arrays.sort(nums, k + 1, nums.length);
  }

}
