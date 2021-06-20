package com.leetcode.random10.sixmonths.medium;

import java.util.Arrays;

public class WiggleSort {

  public void wiggleSort(int[] nums) {
    Arrays.sort(nums);
    int i = 0;
    while (i < nums.length) {
      swap(nums, i + 1, i + 2);
      i += 2;
    }
  }

  private void swap(int[] nums, int i, int j) {
    if (i < nums.length && j < nums.length) {
      int temp = nums[j];
      nums[j] = nums[i];
      nums[i] = temp;
    }
  }

  public static void main(String args[]) {
    WiggleSort w = new WiggleSort();
    int arr[] = new int[]{3, 5, 2, 1, 6, 4};
    w.wiggleSort(arr);
    System.out.println(Arrays.toString(arr));
  }

}
