package com.leetcode.contest;

import java.util.HashMap;

public class NumTriplets {



  public int numTriplets(int[] nums1, int[] nums2) {
    HashMap<Long, Integer> map1 = buildMap(nums1);
    HashMap<Long, Integer> map2 = buildMap(nums2);

    return checkArr(nums1, map2) + checkArr(nums2, map1);
  }

  private int checkArr(int[] nums1, HashMap<Long, Integer> map) {
    int count = 0;
    for (int i = 0; i < nums1.length; i++) {
      Long sqr = Long.valueOf(nums1[i]) * Long.valueOf(nums1[i]);
      if (map.containsKey(sqr)) {
        count += map.get(sqr);
      }
    }
    return count;
  }

  private HashMap<Long, Integer> buildMap(int[] nums2) {
    HashMap<Long, Integer> map = new HashMap();
    for (int i = 0; i < nums2.length; i++) {
      for (int j = i + 1; j < nums2.length; j++) {
        int count1 = map.getOrDefault(Long.valueOf(nums2[i]) * Long.valueOf(nums2[j]), 0);
        map.put(Long.valueOf(nums2[i]) * Long.valueOf(nums2[j]), count1 + 1);
      }
    }
    return map;
  }

  public static void main(String args[]) {
    NumTriplets n = new NumTriplets();
    System.out.println(n.numTriplets(new int[]{1, 1}, new int[]{1, 1, 1}));
  }

}
