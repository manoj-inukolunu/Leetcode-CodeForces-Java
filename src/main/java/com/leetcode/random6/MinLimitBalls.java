package com.leetcode.random6;

public class MinLimitBalls {

  public int minimumSize(int[] nums, int maxOperations) {

    int left = 1, right = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      right = Math.max(nums[i], right);
    }
    int ans = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (possible(mid, nums, maxOperations)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  private boolean possible(int max, int[] nums, int maxOps) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > max) {

        int numSteps;
        if (nums[i] % max == 0) {
          numSteps = (nums[i] / max) - 1;
        } else {
          numSteps = (nums[i] / max);
        }
        if (maxOps >= numSteps) {
          maxOps -= numSteps;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String args[]) {
    MinLimitBalls m = new MinLimitBalls();
    int[] arr = new int[]{7, 17};
    System.out.println(m.minimumSize(arr, 2));
  }

}
