package com.leetcode.random;

public class LongestMountain {

  public int longestMountain(int[] arr) {

    int[] lIncreasing = new int[arr.length];
    lIncreasing[0] = 1;
    int[] rIncreasing = new int[arr.length];
    rIncreasing[rIncreasing.length - 1] = 1;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > arr[i - 1]) {
        lIncreasing[i] = lIncreasing[i - 1] + 1;
      } else {
        lIncreasing[i] = 1;
      }
    }

    //System.out.println(Arrays.toString(lIncreasing));

    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) {
        rIncreasing[i] = rIncreasing[i + 1] + 1;
      } else {
        rIncreasing[i] = 1;
      }
    }
    //System.out.println(Arrays.toString(rIncreasing));
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (lIncreasing[i] > 1 && rIncreasing[i] > 1) {
        max = Math.max(lIncreasing[i] + rIncreasing[i] - 1, max);
      }
    }

    return max < 3 ? 0 : max;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 3, 2};
    LongestMountain l = new LongestMountain();
    System.out.println(l.longestMountain(arr));
  }

}
