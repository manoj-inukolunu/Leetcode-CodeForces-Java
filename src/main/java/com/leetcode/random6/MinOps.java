package com.leetcode.random6;

import java.util.HashMap;

public class MinOps {

  public int minOperations(int[] nums1, int[] nums2) {
    int sum1 = 0, sum2 = 0;
    HashMap<Integer, Integer> map1 = new HashMap<>();
    HashMap<Integer, Integer> map2 = new HashMap<>();
    for (int i = 0; i < nums1.length; i++) {
      sum1 += nums1[i];
      map1.put(nums1[i], map1.getOrDefault(nums1[i], 0) + 1);
    }
    for (int i = 0; i < nums2.length; i++) {
      sum2 += nums2[i];
      map2.put(nums2[i], map2.getOrDefault(nums2[i], 0) + 1);
    }
    if (sum1 == sum2) {
      return 0;
    } else if (sum1 < sum2) {
      return minOps(map1, sum1, sum2);
    } else {
      return minOps(map2, sum2, sum1);
    }
  }

  private int minOps(HashMap<Integer, Integer> map, int currSum, int sum) {
    int ops = 0;
    for (int i = 1; i < 6; i++) {
      if (map.containsKey(i)) {
        int diff = sum - currSum;
        if (diff <= i) {
          return ops + 1;
        }
        int currOps = diff / (6 - i);
        ops += currOps;
        currSum += (currOps * (6 - i));
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    int[] arr1 = new int[]{1, 2, 3, 4, 5, 6};
    int[] arr2 = new int[]{1, 1, 2, 2, 2, 2};
    MinOps m = new MinOps();
    System.out.println(m.minOperations(new int[]{6, 6}, new int[]{1}));

  }


}
