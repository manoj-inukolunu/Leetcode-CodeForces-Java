package com.binarysearch;

import java.util.TreeMap;

public class WindowLimits {

  public boolean solve(int[] nums, int window, int limit) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    TreeMap<Integer, Integer> map = new TreeMap();
    for (int i = 0; i < window; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
      min = Math.min(min, nums[i]);
      max = Math.max(max, nums[i]);
    }
    if (Math.abs(min - max) > limit) {
      return false;
    }
    for (int i = 1; i + window <= nums.length; i++) {
      int val = map.get(nums[i - 1]);
      val--;
      if (val > 0) {
        map.put(nums[i - 1], val);
      } else {
        map.remove(nums[i - 1]);
      }
      map.put(nums[i + window - 1], map.getOrDefault(nums[i + window - 1], 0) + 1);
      int wMin = map.firstKey();
      int wMax = map.lastKey();
      if (Math.abs(wMin - wMax) > limit) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    WindowLimits w = new WindowLimits();
    System.out.println(w.solve(new int[]{1, 3, 7, 5}, 3, 4));
  }

}
