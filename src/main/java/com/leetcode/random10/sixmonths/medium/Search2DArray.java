package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 7/18/20.
 */
public class Search2DArray {

  public boolean searchMatrix(int[][] matrix, int target) {

    int low = 0, high = matrix.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (matrix[mid][0] <= target && matrix[mid][matrix[0].length - 1] >= target) {
        return search(matrix[mid], target);
      }
      if (target < matrix[mid][0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }

  private boolean search(int[] arr, int target) {
    int low = 0, high = arr.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (arr[mid] == target) {
        return true;
      }

      if (target < arr[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return false;
  }


  public static void main(String args[]) {
    Search2DArray s = new Search2DArray();

    int[][] arr = new int[][]{
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}
    };

    System.out.println(s.searchMatrix(arr, 13));
  }
}
