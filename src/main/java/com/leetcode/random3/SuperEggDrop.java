package com.leetcode.random3;

public class SuperEggDrop {

  public int superEggDrop(int K, int N) {
    return drop(K, N, new Integer[K + 1][N + 1]);
  }

  private int drop(int currEggs, int floor, Integer[][] dp) {
    if (floor == 1 || floor == 0 || currEggs == 1) {
      return floor;
    }
    if (dp[currEggs][floor] != null) {
      return dp[currEggs][floor];
    }
    int res = floor;
    int low = 1, high = floor;
    while (low < high) {
      int mid = low + (high - low) / 2;
      int left = drop(currEggs - 1, mid - 1, dp);
      int right = drop(currEggs, floor - mid, dp);
      res = Math.min(res, Math.max(left, right) + 1);
      if (left == right) {
        break;
      } else if (left < right) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    dp[currEggs][floor] = res;
    return res;
  }


  public static void main(String args[]) {
    SuperEggDrop s = new SuperEggDrop();
    System.out.println(s.superEggDrop(3, 14));
  }

}
