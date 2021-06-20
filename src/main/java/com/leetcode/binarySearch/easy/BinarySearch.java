package com.leetcode.binarySearch.easy;

import java.util.Arrays;

/**
 * @author manoji on 2/14/20.
 */
public class BinarySearch {

  public int search(int[] nums, int target) {
    return searchBinary(nums, 0, nums.length - 1, nums.length / 2, target);
  }

  private int searchBinary(int[] nums, int startIndex, int endIndex, int mid, int target) {

    if (startIndex >= endIndex) {
      return -1;
    }
    if (nums[mid] == target) {
      return mid;
    }
    if (startIndex == endIndex && nums[startIndex] == target) {
      return startIndex;
    }
    if (target > nums[mid]) {
      return searchBinary(nums, mid, endIndex, mid + ((endIndex - mid) / 2), target);
    } else {
      return searchBinary(nums, startIndex, mid - 1, startIndex + ((mid - startIndex) / 2), target);
    }
  }

  public static void main(String arg[]) {
    int[] arr = new int[]{2, 3, -2, 4};
    int[] prefixProduct = new int[arr.length];

    prefixProduct[0] = arr[0];

    for (int i = 1; i < arr.length; i++) {
      prefixProduct[i] = prefixProduct[i - 1] * arr[i];
    }

    System.out.println(Arrays.toString(prefixProduct));

    System.out.println(prefixProduct[3] / prefixProduct[2]);
  }


}
