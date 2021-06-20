package com.leetcode;

import java.util.Arrays;

/**
 * @author manoji on 3/13/20.
 */
public class LongestTurbulentSubArray {

  public int maxTurbulenceSize(int[] A) {
    if (A.length == 1) {
      return 1;
    }
    int max = 1;
    int[] dp = new int[A.length];
    Arrays.fill(dp, 1);
    dp[0] = 1;
    dp[1] = A[0] == A[1] ? 1 : 2;
    for (int i = 2; i < A.length; i++) {
      if (A[i] > A[i - 1] && A[i - 1] < A[i - 2]) {
        dp[i] = Math.max(1 + dp[i - 1], 3);
      }
      if (A[i] < A[i - 1] && A[i - 1] > A[i - 2]) {
        dp[i] = Math.max(1 + dp[i - 1], 3);
      }
    }
    for (int i = 0; i < dp.length; i++) {
      if (dp[i] > max) {
        max = dp[i];
      }
    }
    return max;
  }

  private boolean isTurbulent(int[] A, int k, int j) {
    if (j - k + 1 == 2) {
      return true;
    } else {
      for (int i = k + 1; i + 1 <= j; i++) {
        if (A[i + 1] > A[i] && A[i] < A[i - 1]) {
          continue;
        } else if (A[i + 1] < A[i] && A[i] > A[i - 1]) {
          continue;
        } else {
          return false;
        }
      }
      return true;
    }
  }

  public static void main(String args[]) {
    LongestTurbulentSubArray longestTurbulentSubArray = new LongestTurbulentSubArray();
    System.out.println(longestTurbulentSubArray.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
  }

}
