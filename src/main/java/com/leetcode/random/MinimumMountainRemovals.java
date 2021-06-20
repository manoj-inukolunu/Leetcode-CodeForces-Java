package com.leetcode.random;

import java.util.Arrays;

public class MinimumMountainRemovals {

  public int minimumMountainRemovals(int[] nums) {

    int[] lisLeft = new int[nums.length];
    Arrays.fill(lisLeft, 1);
    int[] lisRight = new int[nums.length];
    Arrays.fill(lisRight, 1);

    for (int i = 0; i < nums.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (nums[i] > nums[j]) {
          lisLeft[i] = Math.max(lisLeft[i], lisLeft[j] + 1);
        }
      }
    }

    System.out.println(Arrays.toString(lisLeft));

    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[j] < nums[i]) {
          lisRight[i] = Math.max(lisRight[i], lisRight[j] + 1);
        }
      }
    }

    System.out.println(Arrays.toString(lisRight));

    int max = Integer.MIN_VALUE;
    int minM = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; i++) {
      if (lisLeft[i] > 1 && lisRight[i] > 1) {
        max = Math.max((lisLeft[i] + lisRight[i] - 1), max);
        minM = Math.min(nums.length - max, minM);
      }
    }
    return minM;
  }

  public static void main(String args[]) {
    MinimumMountainRemovals m = new MinimumMountainRemovals();
    int[] arr = new int[]{1, 3, 1};
    System.out.println(m.minimumMountainRemovals(arr));
  }

}
