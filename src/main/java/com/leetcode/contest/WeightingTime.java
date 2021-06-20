package com.leetcode.contest;

public class WeightingTime {

  public double averageWaitingTime(int[][] customers) {
    int chefTime = customers[0][0] + customers[0][1];
    double waitTime = customers[0][1];

    for (int i = 1; i < customers.length; i++) {
      int[] curr = customers[i];
      if (isFree(chefTime, curr)) {
        chefTime = (curr[0] + curr[1]);
        waitTime += (chefTime - curr[0]);
      } else {
        chefTime += curr[1];
        waitTime += (chefTime - curr[0]);
      }
    }
    return waitTime / customers.length;
  }

  private boolean isFree(int chefTime, int[] curr) {
    return chefTime <= curr[0];
  }

  public static void main(String args[]) {
    WeightingTime w = new WeightingTime();
    int[][] arr = new int[][]{
        {2, 3}, {6, 3}, {7, 5}, {11, 3}, {15, 2}, {18, 1}
    };
    System.out.println(w.averageWaitingTime(arr));
  }

}
