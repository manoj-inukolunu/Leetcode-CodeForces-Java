package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

public class SubArrayDivisibleByK {

  public int subarraySum(int[] nums, int k) {
    int count = 0, sum = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int counter = 0;
      while (counter <= sum) {
        if (map.containsKey(sum - counter)) {
          count += map.get(sum - counter);
        }
        counter += k;
      }
      counter = 0;
      while (Math.abs(counter) <= sum) {
        if (map.containsKey(sum - counter)) {
          count += map.get(sum - counter);
        }
        counter -= k;
      }
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    return count;
  }

  public static void main(String args[]) {
    int nums[] = new int[]{4, 5, 0, -2, -3, 1};
    SubArrayDivisibleByK s = new SubArrayDivisibleByK();
    System.out.println(s.subarraySum(nums, 5));
  }

}
