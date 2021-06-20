package com.leetcode.random3;

import java.util.HashMap;

public class CountGoodMeals {

  public int countPairs(int[] nums) {
    int mod = (int) Math.pow(10, 9) + 7;
    int[] arr = new int[21];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) Math.pow(2, i);
    }
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }
    long count = 0;
    for (int key : map.keySet()) {
      for (int pow : arr) {
        int diff = pow - key;
        if (map.containsKey(diff) && diff > key) {
          count = ((count % mod) + (map.get(key) * map.get(diff)) % mod) % mod;
        } else if (map.containsKey(diff) && diff == key) {
          int val = 1;
          val = (val * map.get(diff)) % mod;
          val = (val * (map.get(diff) - 1) / 2) % mod;
          count = (count % mod + val % mod) % mod;
        }
      }
    }
    return (int) count % mod;
  }

  public static void main(String args[]) {
    CountGoodMeals c = new CountGoodMeals();
    System.out.println(1048576 + 1048576);
    System.out.println(Math.log10(2097152) / Math.log10(2));
    System.out.println(c.countPairs(new int[]{4, 4}));
  }
}
