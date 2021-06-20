package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author manoji on 4/1/20.
 */
public class Search2dArray {

  public int[] searchRange(int[] nums, int target) {
    int idx = search(nums, 0, nums.length - 1, target);
    int temp = idx;
    if (idx == -1) {
      return new int[]{-1, -1};
    }
    int ans[] = new int[2];
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    while (idx < nums.length) {
      if (nums[idx] == target && idx > max) {
        max = idx;
        idx++;
      } else {
        break;
      }
    }

    idx = temp;
    while (idx >= 0) {
      if (nums[idx] == target && idx < min) {
        min = idx;
        idx--;
      } else {
        break;
      }
    }
    ans[1] = max;
    ans[0] = min;
    return ans;

  }

  private int search(int[] nums, int begin, int end, int target) {
    if (begin > end) {
      return -1;
    }
    int mid = begin + (end - begin) / 2;
    if (nums[mid] == target) {
      return mid;
    }
    if (target > nums[mid]) {
      return search(nums, mid + 1, end, target);
    }
    return search(nums, begin, mid - 1, target);
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    int rows = matrix.length;
    int columns = matrix[0].length;
    int row = 0;
    int column = columns - 1;
    while (row < rows && column >= 0) {
      int val = matrix[row][column];
      if (target == val) {
        return true;
      }

      if (target < val) {
        column--;
      } else {
        row++;
      }
    }
    return false;
  }


  public static void main(String args[]) {
    int[][] matrix = new int[][]{
        {1, 4, 7, 11, 15},
        {2, 5, 8, 12, 19},
        {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24},
        {18, 21, 23, 26, 30}
    };

    Search2dArray se = new Search2dArray();

    System.out.println(Arrays.toString(se.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
  }

}
