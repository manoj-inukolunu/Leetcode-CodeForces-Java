package com.leetcode.random3;

import java.util.HashMap;

public class ThreeSumMult {

  int mod = (int) Math.pow(10, 9) + 7;

  public int threeSumMulti(int[] A, int target) {
    HashMap<Integer, Integer>[] maps = new HashMap[A.length];
    maps[A.length - 1] = new HashMap<>();
    maps[A.length - 1].put(A[A.length - 1], 1);
    for (int i = A.length - 2; i >= 0; i--) {
      maps[i] = new HashMap(maps[i + 1]);
      maps[i].put(A[i], maps[i].getOrDefault(A[i], 0) + 1);
    }
    int count = 0;
    for (int i = 0; i < A.length; i++) {
      for (int j = i + 1; j < A.length; j++) {
        int needed = target - (A[i] + A[j]);
        if (j + 1 < A.length && maps[j + 1].containsKey(needed)) {
          count = (count % mod + maps[j + 1].get(needed) % mod) % mod;
        }
      }
    }
    return count;
  }

  public static void main(String args[]) {
    ThreeSumMult t = new ThreeSumMult();
    System.out.println(t.threeSumMulti(new int[]{1, 1, 2, 2, 2, 2}, 5));
  }

}
