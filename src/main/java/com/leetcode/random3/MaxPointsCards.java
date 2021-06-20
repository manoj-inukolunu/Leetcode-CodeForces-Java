package com.leetcode.random3;

public class MaxPointsCards {

  public int maxScore(int[] cardPoints, int k) {
    int min = Integer.MAX_VALUE;
    int len = cardPoints.length - k;
    int sum = 0, total = 0;
    for (int i = 0; i < cardPoints.length; i++) {
      total += cardPoints[i];
    }
    for (int i = 0; i < len; i++) {
      sum += cardPoints[i];
    }
    min = Math.min(sum, min);
    for (int i = 1; i + len <= cardPoints.length; i++) {
      sum -= cardPoints[i - 1];
      sum += cardPoints[i + len - 1];
      min = Math.min(min, sum);
    }
    return total - min;
  }


  public static void main(String args[]) {
    int[] arr = new int[]{100, 40, 17, 9, 73, 75};
    MaxPointsCards m = new MaxPointsCards();
    System.out.println(m.maxScore(arr, 3));
  }
}
