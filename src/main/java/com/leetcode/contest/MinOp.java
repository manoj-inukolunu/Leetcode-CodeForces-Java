package com.leetcode.contest;

import java.util.HashMap;

public class MinOp {

  public int minOperations(int[] nums, int x) {

    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }

    sum = sum - x;
    if (sum == 0) {
      return nums.length;
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    int curr = 0, max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      curr += nums[i];
      if (curr == sum) {
        max = i + 1;
      }
      if (map.containsKey(curr - sum)) {
        max = Math.max(i - map.get(curr - sum), max);
      } else {
        map.put(curr, i);
      }
    }

    return max == Integer.MIN_VALUE ? -1 : nums.length - max;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{8828, 9581, 49, 9818, 9974, 9869, 9991, 10000, 10000, 10000, 9999, 9993, 9904, 8819, 1231, 6309};
    int x = 134365;
    MinOp m = new MinOp();
    System.out.println(m.minOperations(arr, x));
  }

}
