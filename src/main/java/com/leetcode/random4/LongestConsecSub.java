package com.leetcode.random4;

import java.util.ArrayList;
import java.util.List;

public class LongestConsecSub {

  public int solve(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int ans = 0;
    for (int i = 0; i < nums.length; i++) {
      List<Integer> list = new ArrayList<>();
      int min = nums[i], max = nums[i];
      list.add(nums[i]);
      for (int j = i + 1; j < nums.length; j++) {
        if (list.size() == max - min + 1) {
          ans = Math.max(ans, list.size());
        }
        list.add(nums[j]);
        if (nums[j] > max) {
          max = nums[j];
        } else if (nums[j] < min) {
          min = nums[j];
        }
      }
      if (list.size() == max - min + 1) {
        ans = Math.max(ans, list.size());
      }
    }
    return ans;
  }


  public static void main(String args[]) {
    LongestConsecSub l = new LongestConsecSub();
    System.out.println(l.solve(new int[]{2, 4, 6, 8, 3, 5, 7}));
  }

}
