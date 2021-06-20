package com.leetcode.binarySearch;

/**
 * @author manoji on 5/12/20.
 */
public class SingleElementInASortedArray {

  Integer ans = null;

  public int singleNonDuplicate(int[] nums) {
    singleNonDuplicate(nums, 0, nums.length - 1);
    return ans;

  }

  private void singleNonDuplicate(int[] nums, int low, int high) {
    //while (low < high) {
    int mid = low + ((high - low) / 2);
    if (mid - 1 >= 0 && nums[mid] != nums[mid - 1]) {
      if (mid + 1 < nums.length) {
        if (nums[mid] != nums[mid + 1]) {
          ans = nums[mid];
          return;
        }
      } else {
        ans = nums[mid];
      }
    }
    if (ans == null && low <= mid - 1) {
      singleNonDuplicate(nums, low, mid - 1);
    }
    if (ans == null && mid + 1 <= high) {
      singleNonDuplicate(nums, mid + 1, high);
    }
    //}
  }

  public static void main(String args[]) {
    SingleElementInASortedArray s = new SingleElementInASortedArray();
    System.out.println(s.singleNonDuplicate(new int[]{1, 1, 2}));
  }

}
