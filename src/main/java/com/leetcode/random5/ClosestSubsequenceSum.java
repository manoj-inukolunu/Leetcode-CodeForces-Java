package com.leetcode.random5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ClosestSubsequenceSum {

  public int minAbsDifference(int[] nums, int goal) {
    List<Integer> a = new ArrayList<>();
    List<Integer> b = new ArrayList<>();
    int mid = nums.length / 2;
    subsetSum(Arrays.copyOfRange(nums, 0, mid), 0, a, 0);
    subsetSum(Arrays.copyOfRange(nums, mid, nums.length), 0, b, 0);
    TreeSet<Integer> set = new TreeSet<>(a);
    int min = Integer.MAX_VALUE;
    for (int val : b) {
      int curr = goal - val;
      Integer lower = set.lower(curr);
      Integer higher = set.higher(curr);
      if (set.contains(curr)) {
        min = Math.min(min, Math.abs(val + curr - goal));
      }
      if (lower != null && higher != null) {
        if (Math.abs(lower - curr) <= Math.abs(higher - curr)) {
          min = Math.min(min, Math.abs(lower + val - goal));
        } else {
          min = Math.min(min, Math.abs(higher + val - goal));
        }
      } else if (lower == null && higher != null) {
        min = Math.min(min, Math.abs(higher + val - goal));
      } else if (lower != null) {
        min = Math.min(min, Math.abs(lower + val - goal));
      }
    }

    return min;
  }

  private void subsetSum(int[] nums, int idx, List<Integer> list, int sum) {
    if (idx >= nums.length) {
      list.add(sum);
      return;
    }
    subsetSum(nums, idx + 1, list, sum + nums[idx]);
    subsetSum(nums, idx + 1, list, sum);
  }

  public static void main(String args[]) {
    ClosestSubsequenceSum c = new ClosestSubsequenceSum();
    System.out.println(c.minAbsDifference(new int[]{5, -7, 3, 5}, 6));
  }
}
