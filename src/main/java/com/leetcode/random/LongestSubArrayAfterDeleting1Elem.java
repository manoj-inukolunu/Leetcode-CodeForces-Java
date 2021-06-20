package com.leetcode.random;

import java.util.TreeSet;

public class LongestSubArrayAfterDeleting1Elem {

  public int longestSubarray(int[] nums) {
    TreeSet<Integer> set = new TreeSet<>();
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        set.add(i);
      }
      sum += nums[i];
    }
    if (sum == nums.length) {
      return nums.length - 1;
    }
    if (sum == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        int len = getLen(set, i, nums);
        max = Math.max(len, max);
      }
    }
    return max;
  }

  int getLen(TreeSet<Integer> set, int idx, int[] nums) {
    Integer lower = set.lower(idx);
    Integer higher = set.higher(idx);
    int left = 0, right = 0;
    if (lower == null) {
      left = idx;
    } else {
      left = idx - lower - 1;
    }
    if (higher == null) {
      right = nums.length - idx - 1;
    } else {
      right = higher - idx - 1;
    }
    if (idx - 1 >= 0 && nums[idx - 1] == 0) {
      left = 0;
    }
    if (idx + 1 < nums.length && nums[idx + 1] == 0) {
      right = 0;
    }
    return left + right;
  }

  public static void main(String args[]) {
    LongestSubArrayAfterDeleting1Elem l = new LongestSubArrayAfterDeleting1Elem();
    int[] arr = new int[]{1, 1, 0, 0, 1, 1, 1, 0, 1};
    System.out.println(l.longestSubarray(arr));
  }

}
