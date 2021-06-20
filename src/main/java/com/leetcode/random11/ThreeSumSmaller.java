package com.leetcode.random11;

import java.util.Arrays;
import java.util.TreeMap;

public class ThreeSumSmaller {

  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int count = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int curr = target - (nums[i] + nums[j]);
        Integer lower = map.lowerKey(curr);
        if (lower != null && map.get(lower) >= j + 1) {
          count += (map.get(lower) - (j + 1)) + 1;
        }
      }
    }
    return count;
  }


  public static void main(String args[]) {
    ThreeSumSmaller t = new ThreeSumSmaller();
    System.out.println(t.threeSumSmaller(new int[]{0}, 0));
  }

}
