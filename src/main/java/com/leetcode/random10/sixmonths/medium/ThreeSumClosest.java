package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class ThreeSumClosest {

  int min = Integer.MAX_VALUE;
  int ans = Integer.MAX_VALUE;

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      twoSum(nums, nums[i], i, target);
    }
    return ans;
  }

  private void twoSum(int[] nums, int curr, int idx, int target) {
    int low = idx + 1, high = nums.length - 1;
    while (low < high) {
      int sum = nums[low] + nums[high] + curr;
//      System.out.println(sum + " " + target);
      int diff;
      if (target >= sum) {
        diff = target - sum;
      } else {
        diff = sum - target;
      }
      if (diff < min) {
        min = diff;
        ans = sum;
      }
      if (sum < target) {
        low++;
      } else {
        high--;
      }

    }
  }

  public static void main(String args[]) {
    ThreeSumClosest t = new ThreeSumClosest();
    System.out.println(t.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
  }

}
