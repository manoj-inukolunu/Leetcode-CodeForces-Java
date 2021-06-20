package com.leetcode.random;

import java.util.Arrays;
import java.math.BigInteger;


public class MaxSumRange {

  public int maxSumRangeQuery(int[] nums, int[][] requests) {
    Arrays.sort(nums);
    int[] arr = diffArray(requests, nums.length);
    int j = nums.length - 1;
    BigInteger sum = BigInteger.ZERO;
    int mod = (int) Math.pow(10, 9) + 7;
    for (int i = arr.length - 1; i >= 0; i--) {
      sum = sum.add(BigInteger.valueOf(nums[j--]).multiply(BigInteger.valueOf(arr[i])));
    }
    return sum.mod(BigInteger.valueOf(mod)).intValue();
  }

  public int[] diffArray(int[][] requests, int len) {
    int[] arr = new int[len];
    for (int i = 0; i < requests.length; i++) {
      int[] curr = requests[i];
      arr[curr[0]] += 1;
      if (curr[1] + 1 < arr.length) {
        arr[curr[1] + 1] += -1;
      }
    }

    for (int i = 1; i < arr.length; i++) {
      arr[i] = arr[i - 1] + arr[i];
    }
    Arrays.sort(arr);
    return arr;
  }


  public static void main(String args[]) {
    MaxSumRange m = new MaxSumRange();
    int[][] req = new int[][]{
        {1, 3}, {0, 2}, {1, 1}
    };
  }

}
