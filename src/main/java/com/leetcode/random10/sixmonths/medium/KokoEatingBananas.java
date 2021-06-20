package com.leetcode.random10.sixmonths.medium;

public class KokoEatingBananas {

  private boolean canEat(int[] arr, int speed, int total) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      int val = (int) Math.ceil((double) arr[i] / speed);
      sum += val;
    }
    return sum <= total;
  }

  public int minEatingSpeed(int[] piles, int H) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < piles.length; i++) {
      if (piles[i] > max) {
        max = piles[i];
      }
    }

    int low = 1, high = max;
    while (low < high) {
      int speed = low + (high - low) / 2;
      if (canEat(piles, speed, H)) {
        high = speed;
      } else {
        low = speed + 1;
      }
    }

    return low;
  }

}
