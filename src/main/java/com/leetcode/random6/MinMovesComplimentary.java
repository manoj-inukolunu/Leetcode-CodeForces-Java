package com.leetcode.random6;

import java.util.HashMap;

public class MinMovesComplimentary {

  public int minMoves(int[] nums, int limit) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length / 2; i++) {
      map.put(nums[i] + nums[nums.length - 1 - i], map.getOrDefault(nums[i] + nums[nums.length - 1 - i], 0) + 1);
    }
    int sum = -1, freq = -1;
    for (int key : map.keySet()) {
      if (key <= 2 * limit && key >= 2) {
        if (freq == -1) {
          freq = map.get(key);
          sum = key;
        } else if (freq > map.get(key)) {
          freq = map.get(key);
          sum = key;
        }
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int key : map.keySet()) {
      int count = 0;
      sum = key;
      for (int i = 0; i < nums.length / 2; i++) {
        if (nums[i] + nums[nums.length - i - 1] != sum) {
          if (sum - nums[i] >= 1 && sum - nums[i] <= limit) {
            count++;
          } else if (sum - nums[nums.length - i - 1] >= 1 && sum - nums[nums.length - 1 - i] <= limit) {
            count++;
          } else {
            count += 2;
          }
        }
      }
      ans = Math.min(ans, count);
    }
    return ans;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{20744, 7642, 19090, 9992, 2457, 16848, 3458, 15721};
    MinMovesComplimentary m = new MinMovesComplimentary();
    System.out.println(m.minMoves(arr, 22891));
  }
}
