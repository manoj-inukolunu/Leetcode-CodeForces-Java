package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {


  public List<List<Integer>> permuteUnique(int[] nums) {

    Arrays.sort(nums);

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      list.add(nums[i]);
    }

    Set<List<Integer>> collector = new HashSet<>();
    permute(list, nums.length, collector);

    return new ArrayList<>(collector);
  }

  private void permute(List<Integer> nums, int length, Set<List<Integer>> collector) {
    if (length == 1) {
      collector.add(new ArrayList<>(nums));
      return;
    }

    for (int i = 0; i < length; i++) {
      Collections.swap(nums, i, length - 1);
      permute(nums, length - 1, collector);
      Collections.swap(nums, i, length - 1);
    }
  }

  public static void main(String args[]) {
    Permutations p = new Permutations();
    System.out.println(p.permuteUnique(new int[]{1, 1, 2}));
  }

}
