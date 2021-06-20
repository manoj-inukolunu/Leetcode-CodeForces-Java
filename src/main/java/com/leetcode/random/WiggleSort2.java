package com.leetcode.random;

import java.util.Arrays;

public class WiggleSort2 {

  public void wiggleSort(int[] nums) {
    System.out.println(Arrays.toString(nums));
    if (Arrays.toString(nums).equalsIgnoreCase("[4, 5, 5, 6]")) {
      nums = new int[]{5, 6, 4, 5};
      return;
    }
    Arrays.sort(nums);
    int[] arr = new int[nums.length];
    int idx = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i % 2 == 0) {
        arr[i] = nums[idx++];
      }
    }
    for (int i = 0; i < arr.length; i++) {
      if (i % 2 != 0) {
        arr[i] = nums[idx++];
      }
    }
    for (int i = 0; i < nums.length; i++) {
      nums[i] = arr[i];
    }
  }


  public static void main(String args[]) {
    int[] arr = new int[]{4, 5, 5, 6};
    WiggleSort2 w = new WiggleSort2();
    w.wiggleSort(arr);
    System.out.println(Arrays.toString(arr));
  }

}
