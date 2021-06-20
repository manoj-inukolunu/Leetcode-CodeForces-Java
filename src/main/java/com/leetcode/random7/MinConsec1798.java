package com.leetcode.random7;

import java.util.Arrays;

public class MinConsec1798 {

  public int getMaximumConsecutive(int[] coins) {

    Arrays.sort(coins);
    int val = 0, i = 1;
    while (true) {
      int idx = Arrays.binarySearch(coins, i);
      if (idx < 0 && !possible(idx, coins, i)) {
        break;
      } else {
        val = i;
      }
      i++;
    }
    return val + 1;
  }

  private boolean possible(int idx, int[] coins, int sum) {
    int curr = -(idx + 1);
    for (int i = curr - 1; i >= 0; i--) {
      if (sum - coins[i] >= 0) {
        sum -= coins[i];
      }
      if (sum == 0) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    MinConsec1798 m = new MinConsec1798();
    System.out.println(m.getMaximumConsecutive(new int[]{1, 1, 1, 4}));
    int val = (int) Math.pow(10, 9);
    System.out.println((long) val * (val + 1) / 2);
  }

}
