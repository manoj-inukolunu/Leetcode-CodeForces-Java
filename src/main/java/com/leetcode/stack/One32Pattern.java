package com.leetcode.stack;

import java.util.TreeSet;

/**
 * @author manoji on 5/13/20.
 */
public class One32Pattern {

  public boolean find132pattern(int[] nums) {
    if (nums.length < 3) {
      return false;
    }
    int[] min = new int[nums.length];
    min[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min[i] = Math.min(nums[i], min[i - 1]);
    }
    TreeSet<Integer> set = new TreeSet<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      int curr = nums[i];
      Integer lower = set.lower(curr);
      if (lower != null) {
        if (lower > min[i]) {
          return true;
        }

      }
      set.add(curr);
    }
    return false;
  }

  public static void main(String args[]) {
    One32Pattern o = new One32Pattern();
    int[] arr = new int[]{-2, 1, 2, -2, 1, 2};

    System.out.println(o.find132pattern(arr));

  }
}
