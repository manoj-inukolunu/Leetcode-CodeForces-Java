package com.leetcode.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author manoji on 3/1/20.
 */
public class Easy1365 {

  public int[] smallerNumbersThanCurrent(int[] nums) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[j] < nums[i] && i != j) {
          if (map.containsKey(i)) {
            map.put(i, map.get(i) + 1);
          } else {
            map.put(i, 1);
          }
        } else {
          if (!map.containsKey(i)) {
            map.put(i, 0);
          }
        }
      }
    }
    int[] finalArr = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      finalArr[i] = map.get(i);
    }
    return finalArr;
  }

  public static void main(String args[]) {
    Easy1365 easy1365 = new Easy1365();

    int[] arr = easy1365.smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3});

    System.out.println(Arrays.toString(arr));
  }

}
