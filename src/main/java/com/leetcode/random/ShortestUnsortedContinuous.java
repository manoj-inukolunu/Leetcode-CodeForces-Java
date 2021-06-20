package com.leetcode.random;

import java.util.Arrays;

public class ShortestUnsortedContinuous {

  public int findUnsortedSubarray(int[] nums) {
    int[] arr = nums.clone();
    Arrays.sort(arr);
    int left = -1, right = -1;
    for (int i = 0; i < nums.length; i++) {
      if (arr[i] != nums[i]) {
        left = i;
        break;
      }
    }
    for (int j = nums.length - 1; j >= 0; j--) {
      if (arr[j] != nums[j]) {
        right = j;
        break;
      }
    }
    if (left != -1 && right != -1) {
      return right - left + 1;
    } else {
      return 0;
    }
  }

  public static void main(String args[]) {
    ShortestUnsortedContinuous s = new ShortestUnsortedContinuous();
    int[] arr = new int[]{1, 2, 3, 4};
    System.out.println(s.findUnsortedSubarray(arr));
  }

}
