package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

public class MissingElement {

  public int missingElement(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    int max = nums[nums.length - 1];
    int i = 0;
    while (i < nums.length) {
      if (!map.containsKey(nums[i] + 1)) {
        break;
      }
      i++;
    }
    if (k == 1) {
      return nums[i] + 1;
    } else {
      int j = 1, ans = 1;
      while (ans <= k) {
        if (nums[i] + j > max) {
          return max + (k - ans) + 1;
        }
        if (!map.containsKey(nums[i] + j)) {
          if (ans == k) {
            return nums[i] + j;
          }
          j++;
          ans++;
        } else {
          i = map.get(nums[i] + j);
          j = 1;
        }
      }
      return nums[i] + j;
    }
  }


  public static void main(String args[]) {
    MissingElement m = new MissingElement();
    System.out.println(m.missingElement(new int[]{1, 2, 4}, 5));
  }

}
