package com.leetcode.random;

public class CapacityToShip {


  public boolean canShip(int[] weights, int totalDays, int maxCapacity) {
    int days = 0;
    int sum = 0;
    for (int i = 0; i < weights.length; i++) {
      if (sum + weights[i] <= maxCapacity) {
        sum += weights[i];
      } else {
        sum = weights[i];
        days++;
      }
    }
    return days < totalDays;
  }


  public int shipWithinDays(int[] weights, int D) {
    int left = weights[0];
    int right = 0;
    for (int num : weights) {
      right += num;
      left = Math.max(num, left);
    }
    int ans = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (canShip(weights, D, mid)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    CapacityToShip c = new CapacityToShip();
    int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(c.shipWithinDays(arr, 5));
  }

}
