package com.leetcode.random;

import java.util.HashMap;

public class KSumPairs {

  public int maxOperations(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int total = 0;
    for (int i = 0; i < nums.length; i++) {
      int curr = nums[i];
      int next = k - curr;
      if (curr == next && map.containsKey(curr) && map.get(curr) >= 2) {
        total++;
        int cCount = map.get(curr);
        if (cCount - 2 > 0) {
          map.put(curr, cCount - 2);
        } else {
          map.remove(curr);
        }
      } else if (curr != next && map.containsKey(next)) {
        total++;
        int cCount = map.get(curr);
        int nCount = map.get(next);
        if (cCount - 1 > 0) {
          map.put(curr, cCount - 1);
        } else {
          map.remove(curr);
        }
        if (nCount - 1 > 0) {
          map.put(next, nCount - 1);
        } else {
          map.remove(next);
        }
      }
    }
    return total;

  }

  public static void main(String args[]) {
    int[] nums = new int[]{1, 2, 3, 4};
    KSumPairs k = new KSumPairs();
    System.out.println(k.maxOperations(nums, 5));
  }

}
