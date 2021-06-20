package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author manoji on 3/18/20.
 */
public class Subsets {

  List<List<Integer>> subsets = new ArrayList();

  public List<List<Integer>> subsets(int[] nums) {
    generate(nums, 0, new ArrayList());
    return subsets;
  }

  private void generate(int[] nums, int index, List<Integer> list) {
    if (index >= nums.length) {
      List<Integer> list1 = new ArrayList<>(list);
      //Collections.copy(list1,list);
      if (!subsets.contains(list1)) {
        subsets.add(list1);
      }
      return;
    }
    generate(nums, index + 1, list);
    list.add(nums[index]);
    generate(nums, index + 1, list);
    list.remove(Integer.valueOf(nums[index]));
  }

  public static void main(String args[]) {
    Subsets subsets = new Subsets();

    System.out.println(subsets.subsets(new int[]{1, 2, 2}));
  }
}
