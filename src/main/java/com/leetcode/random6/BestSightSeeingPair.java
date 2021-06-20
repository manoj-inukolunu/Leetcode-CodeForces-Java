package com.leetcode.random6;

public class BestSightSeeingPair {

  public int maxScoreSightseeingPair(int[] A) {
    int max = Integer.MIN_VALUE;
    int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; i++) {
      first = Math.max(A[i]+i,first);
    }
    return max;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{75, 6, 93, 30, 5, 13, 19, 98, 46, 16, 9, 10, 29, 57, 6, 70, 55, 95, 94, 47, 3, 30, 42, 29, 74, 10, 11, 15, 81, 52, 24, 58,
        76, 59, 19, 66, 26, 87, 47, 33, 96, 2, 67, 52, 53, 86, 12, 36, 2, 41, 35, 53, 64, 90, 54, 84, 99, 67, 9, 81, 100, 52, 66, 37, 32, 14, 14, 23,
        31, 82, 96, 81, 96, 22, 17, 68, 3, 88, 59, 54, 23, 22, 77, 61, 16, 46, 22, 94, 50, 29, 46, 7, 33, 22, 99, 31, 99, 75, 67, 95};
    BestSightSeeingPair b = new BestSightSeeingPair();
    System.out.println(b.maxScoreSightseeingPair(arr));
  }
}
