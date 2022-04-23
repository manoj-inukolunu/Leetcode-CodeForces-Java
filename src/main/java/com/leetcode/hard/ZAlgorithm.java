package com.leetcode.hard;

import java.util.Arrays;

public class ZAlgorithm {

  private int[] zFunction(String str) {
    int n = (int) str.length();
    int[] z = new int[str.length()];
    for (int i = 1, l = 0, r = 0; i < n; ++i) {
      if (i <= r) {
        z[i] = Math.min(r - i + 1, z[i - l]);
      }
      while (i + z[i] < n && str.charAt(z[i]) == str.charAt(i + z[i])) {
        ++z[i];
      }
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }
    }
    z[0] = str.length();
    return z;
  }

  int sum(int[] z) {
    return Arrays.stream(z).sum();
  }

  public static void main(String[] args) {
    String str = "babab";
    ZAlgorithm z = new ZAlgorithm();
    System.out.println(z.sum(z.zFunction(str)));
  }
}
