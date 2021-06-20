package com.leetcode.random10.sixmonths.medium;

import org.jetbrains.annotations.NotNull;

/**
 * @author manoji on 7/31/20.
 */
public class FindPeak {

  public int findPeakElement(int[] nums) {
    return findPeak(nums, 0, nums.length - 1);
  }


  private int findPeak(@NotNull int[] nums, int low, int high) {
    int mid = low + (high - low) / 2;
    if (nums[mid] < nums[mid - 1]) {
      return findPeak(nums, low, mid - 1);
    } else if (nums[mid] < nums[mid + 1]) {
      return findPeak(nums, mid + 1, high);
    } else {
      return mid;
    }
  }

  public static void main(String args[]) {
    FindPeak f = new FindPeak();

    System.out.println(f.findPeakElement(new int[]{1, 2, 3, 4}));
  }

}
