package com.leetcode.random;

public class PeakIndex {

  public int peakIndexInMountainArray(int[] arr) {

    int start = 0, end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (mid - 1 >= 0 && mid + 1 < arr.length) {
        if (mid - 1 >= 0 && mid + 1 < arr.length && arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]) {
          return mid;
        }
        if (arr[mid - 1] < arr[mid] && arr[mid] < arr[mid + 1]) {
          start = mid + 1;
        } else {
          end = mid;
        }
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    PeakIndex p = new PeakIndex();
    int[] arr = new int[]{3, 5, 3, 2, 0};
    System.out.println(p.peakIndexInMountainArray(arr));
  }

}
