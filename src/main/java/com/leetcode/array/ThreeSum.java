package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 5/25/20.
 */
public class ThreeSum {

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    Set<List<Integer>> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int rem = Arrays.binarySearch(nums, -(nums[i] + nums[j]));
        if (rem >= 0 && rem != i && rem != j) {
          List<Integer> list = new ArrayList<>();
          list.add(nums[i]);
          list.add(nums[j]);
          list.add(nums[rem]);
          Collections.sort(list);
          set.add(list);
        }
      }
    }
    return new ArrayList<>(set);
  }


  public static void main(String args[]) {
    ThreeSum t = new ThreeSum();

    System.out.println(t.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
  }

}
