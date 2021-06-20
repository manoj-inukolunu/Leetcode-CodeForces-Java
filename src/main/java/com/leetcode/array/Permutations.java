package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author manoji on 4/19/20.
 */
public class Permutations {

  //Heaps Alogrithm

  List<List<Integer>> list = new ArrayList<>();

  public List<List<Integer>> permute(int[] nums) {
    permutation1(nums, nums.length);

    //heapsAlgorithm(nums.length, nums, list);
    return list;
  }


  public void permutation1(int[] a, int n) {
    if (n == 1) {
      list.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
      return;
    }
    for (int i = 0; i < n; i++) {
      swap(a, i, n - 1);  // (remove the ith element)
      permutation1(a, n - 1);
      swap(a, i, n - 1);  // (restore for the next round)
    }
  }


  private void heapsAlgorithm(int k, int[] nums, List<List<Integer>> lists) {
    if (k == 1) {
      lists.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      return;
    }
    heapsAlgorithm(k - 1, nums, lists);
    for (int i = 0; i < k - 1; i++) {
      if (k % 2 == 0) {
        swap(nums, i, k - 1);
      } else {
        swap(nums, 0, k - 1);
      }
      heapsAlgorithm(k - 1, nums, lists);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String args[]) {
    Permutations p = new Permutations();
    System.out.println(p.permute(new int[]{1, 2, 3}));
  }

}
