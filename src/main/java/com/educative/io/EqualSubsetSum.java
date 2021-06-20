package com.educative.io;

import java.util.HashMap;

/**
 * @author manoji on 2019-10-15.
 */
public class EqualSubsetSum {

  public static boolean subsetSum(int[] nums, int sum) {
    HashMap<String, Boolean> map = new HashMap<>();
    return exists(nums, 0, sum, map);
  }

  public static void main(String args[]) {
    int[] nums = new int[]{1, 3, 4, 8};
    System.out.println(subsetSum(nums, 6));
  }


  public static boolean exists(int[] nums, int currentIndex, int sum, HashMap<String, Boolean> map) {

    if (map.containsKey(currentIndex + "-" + sum)) {
      return map.get(currentIndex + "-" + sum);
    }

    if (sum == 0) {
      return true;
    }
    if (sum < 0) {
      return false;
    }
    if (currentIndex < nums.length) {
      boolean val = exists(nums, currentIndex + 1, sum, map) || exists(nums, currentIndex + 1, sum - nums[currentIndex], map);
      map.put(currentIndex + "-" + sum, val);
      return val;
    }
    return false;
  }

}
