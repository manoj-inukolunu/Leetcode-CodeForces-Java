package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

public class MaxSubArraySumEqualsK {

  public int maxSubArrayLen(int[] nums, int k) {
    int max = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      if (sum == k) {
        max = i + 1;
      } else if (map.containsKey(sum - k)) {
        max = Math.max(i - map.get(sum - k), max);
      }
      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }
    return max;
  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }


  public static void main(String args[]) {
    MaxSubArraySumEqualsK m = new MaxSubArraySumEqualsK();

    System.out.println(m.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
  }

}
