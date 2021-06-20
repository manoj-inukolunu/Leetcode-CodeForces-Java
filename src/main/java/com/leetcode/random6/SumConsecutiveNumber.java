package com.leetcode.random6;

public class SumConsecutiveNumber {
  static int countConsecutive(int N) {
    int count = 0;
    for (int L = 1; L * (L + 1) < 2 * N; L++) {
      double a = (1.0 * N - (L * (L + 1)) / 2) / (L + 1);
      if (a - (long) a == 0.0) {
        count++;
      }
    }
    return count+1;
  }

  // Driver code to test above function
  public static void main(String[] args) {
    int N = 1000000000;
    System.out.println(countConsecutive(N));
  }
}

