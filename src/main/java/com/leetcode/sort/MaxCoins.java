package com.leetcode.sort;

import java.util.Arrays;

public class MaxCoins {

  public int maxCoins(int[] piles) {
    Arrays.sort(piles);
    int alice = piles.length - 1, me = 0, bob = 0;
    int aliceSum = 0, bobSum = 0, meSum = 0;
    int count = piles.length / 3;
    while (count > 0) {
      count--;
      aliceSum += (piles[alice]);
      meSum += (piles[alice - 1]);
      alice -= 2;
      bobSum += (piles[bob++]);
    }
    return meSum;
  }

  public static void main(String args[]) {
    MaxCoins m = new MaxCoins();
    int arr[] = new int[]{2, 4, 1, 2, 7, 8};
    System.out.println(m.maxCoins(arr));
  }
}
