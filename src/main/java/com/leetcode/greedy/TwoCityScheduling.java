package com.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author manoji on 6/4/20.
 */
public class TwoCityScheduling {

  public void sortbyColumn(int arr[][], int col) {
    Arrays.sort(arr, new Comparator<int[]>() {
      @Override
      public int compare(final int[] entry1,
          final int[] entry2) {
        if (entry1[col] > entry2[col]) {
          return 1;
        } else {
          return -1;
        }
      }
    });
  }

  public int twoCitySchedCost(int[][] costs) {
    int min = 0;
    int countA = 0;
    for (int i = 0; i < costs.length; i++) {
      if (costs[i][0] <= costs[i][1] && countA++ < costs.length / 2) {
        min += costs[i][0];
      } else {
        min += costs[i][1];
      }
    }
    return min;
  }


  public static void main(String args[]) {
    TwoCityScheduling t = new TwoCityScheduling();

    int[][] costs = new int[][]{
        {10, 20},
        {30, 200},
        {400, 50},
        {30, 20}
    };

    System.out.println(t.twoCitySchedCost(costs));
  }
}
