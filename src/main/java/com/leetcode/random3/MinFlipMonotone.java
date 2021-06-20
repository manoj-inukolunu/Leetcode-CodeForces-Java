package com.leetcode.random3;

public class MinFlipMonotone {

  public int minFlipsMonoIncr(String S) {
    int[] left = new int[S.length()];
    int[] right = new int[S.length()];
    for (int i = 1, j = S.length() - 2; i < left.length && j >= 0; i++, j--) {
      left[i] += left[i - 1] + (S.charAt(i - 1) == '0' ? 0 : 1);
      right[j] += right[j + 1] + (S.charAt(j) == '1' ? 0 : 1);
    }
    int count = Integer.MAX_VALUE;
    for (int i = 0; i + 1 < S.length(); i++) {
      count = Math.min(count, left[i] + right[i]);
    }
    return count;
  }

  public static void main(String args[]) {
    MinFlipMonotone m = new MinFlipMonotone();
    System.out.println(m.minFlipsMonoIncr("00110"));
  }

}
