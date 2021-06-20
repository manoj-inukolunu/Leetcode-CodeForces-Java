package com.leetcode.random;

import java.util.ArrayList;
import java.util.List;

public class GetMaxLen {

  public int getMaxLen(int[] nums) {
    List<Integer> zero = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        zero.add(i);
      }
    }
   // System.out.println(zero);
    if (zero.isEmpty()) {
      return getMaxLen(nums, 0, nums.length);
    } else {
      int max = Integer.MIN_VALUE;
      int prev = -1;
      for (int i = 0; i < zero.size(); i++) {
        if (prev == -1) {
          max = Math.max(max, getMaxLen(nums, 0, zero.get(i)));
        } else {
          max = Math.max(max, getMaxLen(nums, prev + 1, zero.get(i)));
        }
        prev = zero.get(i);
      }
      max = Math.max(max, getMaxLen(nums, zero.get(zero.size() - 1) + 1, nums.length));
      return max;
    }
  }

  public int getMaxLen(int[] nums, int start, int end) {
    int count = 0, prev = -1, first = -1;
    for (int i = start; i < end; i++) {
      if (nums[i] < 0) {
        count++;
        prev = i;
        if (first == -1) {
          first = i;
        }
      }
    }
    if (count % 2 == 0) {
      return end - start;
    } else {
      int remStart = end - first - 1;
      int remEnd = prev - start;
      int m = Math.max(remStart, remEnd);
      return Math.max(m, end - prev - 1);
    }
  }

  public static void main(String args[]) {
    GetMaxLen g = new GetMaxLen();
    int[] arr = new int[]{0, -19, 26, -24, -13, -2, 26, 10, 0, 4, 0, -26, -22, 9, 35, -11, -14, 0, -29};
    System.out.println(g.getMaxLen(arr));
  }

}
