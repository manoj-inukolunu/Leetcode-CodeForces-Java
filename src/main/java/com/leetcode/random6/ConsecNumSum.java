package com.leetcode.random6;

public class ConsecNumSum {

  public int consecutiveNumbersSum(int N) {
    int count = 0;
    long c = -2L * N;
    for (int i = 1; (i * (i + 1)) <= 2 * N; i++) {
      double b = (2L * i - 1);
      double val1 = (-b + Math.sqrt((b * b) - (4 * c))) / 2;
      double val2 = (-b - Math.sqrt((b * b) - (4 * c))) / 2;
      if (isInt(val1) && val1 > 0) {
        count++;
      }
      if (isInt(val2) && val2 > 0) {
        count++;
      }
    }
    return count + 1;
  }

  int countConsecutive(int N) {
    int count = 0;
    for (int L = 1; L * (L + 1) < 2 * N; L++) {
      float a = (float) ((1.0 * N - (L * (L + 1)) / 2) / (L + 1));
      if (a - (int) a == 0.0) {
        count++;
      }
    }
    return count;
  }

  public static boolean isInt(double d) {
    return d == (int) d;
  }

  public static void main(String args[]) {
    ConsecNumSum c = new ConsecNumSum();
    System.out.println(c.countConsecutive(78729668));
  }

}
