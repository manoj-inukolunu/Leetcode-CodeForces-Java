package com.leetcode.random1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MaxErasure {


  public int maximumUniqueSubarray(int[] arr) {
    int max = Integer.MIN_VALUE;
    int[] pre = new int[arr.length];
    pre[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      pre[i] = pre[i - 1] + arr[i];
    }
    HashMap<Integer, Integer> map = new HashMap();
    int end = 0, start = 0;
    int i = 0;
    while (i < arr.length) {
      if (!map.containsKey(arr[i])) {
        end++;
        map.put(arr[i], i);
        i++;
      } else {
        max = Math.max(max, getSum(pre, start, start + end - 1));
        end = 0;
        i = map.get(arr[i]) + 1;
        start = i;
        map.clear();
      }
    }
    return Math.max(max, getSum(pre, start, start + end - 1));
  }

  public int lengthOfLongestSubstring(int[] nums) {
    int n = nums.length;
    Set<Integer> set = new HashSet<>();
    int ans = 0, i = 0, j = 0;
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int t = 1; t < nums.length; t++) {
      pre[t] = pre[t - 1] + nums[t];
    }
    while (i < n && j < n) {
      if (!set.contains(nums[j])) {
        set.add(nums[j++]);
        ans = Math.max(ans, getSum(pre, i, j - 1));
      } else {
        set.remove(nums[i++]);
      }
    }
    return ans;
  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    int[] arr = new int[]{187, 470, 25, 436, 538, 809, 441, 167, 477, 110, 275, 133, 666, 345, 411, 459, 490, 266, 987, 965, 429, 166, 809, 340, 467,
        318, 125, 165, 809, 610, 31, 585, 970, 306, 42, 189, 169, 743, 78, 810, 70, 382, 367, 490, 787, 670, 476, 278, 775, 673, 299, 19, 893, 817,
        971, 458, 409, 886, 434};
    MaxErasure m = new MaxErasure();
    System.out.println(m.lengthOfLongestSubstring(arr));
  }

}
