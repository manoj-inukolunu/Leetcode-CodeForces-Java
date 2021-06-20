package com.leetcode.random6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSmaller {


  public List<Integer> countSmaller(int[] nums) {
    List<Integer> list = new ArrayList<>();
    int[] ans = new int[nums.length];
    for (int i = nums.length - 1; i >= 0; i--) {
      if (list.size() == 0) {
        list.add(nums[i]);
        ans[i] = 0;
      } else {
        int idx = Collections.binarySearch(list, nums[i]);
        idx = -(idx + 1);
        ans[i] = idx;
        list.add(idx, nums[i]);
      }
    }
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < ans.length; i++) {
      ret.add(ans[i]);
    }
    return ret;
  }

  public static void main(String args[]) {
    CountSmaller c = new CountSmaller();
    System.out.println(c.countSmaller(new int[]{5, 2, 6, 1}));
  }

}
