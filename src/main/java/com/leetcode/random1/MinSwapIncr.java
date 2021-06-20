package com.leetcode.random1;

import java.util.HashMap;

public class MinSwapIncr {

  public int minSwap(int[] A, int[] B) {
    HashMap<String, Integer> map = new HashMap<>();
    return Math.min(solve(A, B, 1, false, map), solve(A, B, 1, true, map));

  }

  private int solve(int[] a, int[] b, int idx, boolean swapped, HashMap<String, Integer> map) {
    String key = idx + "|" + swapped;
    if (map.containsKey(key)) {
      return map.get(key);
    }
    if (idx >= a.length) {
      return 0;
    }
    int min, withSwap, withoutSwap;
    if (a[idx - 1] >= a[idx] || b[idx - 1] >= b[idx]) {
      //have to swap
      swap(a, b, idx);
      withSwap = 1 + solve(a, b, idx + 1, true, map);
      min = withSwap;
      swap(a, b, idx);
    } else {
      swap(a, b, idx);
      withSwap = 1 + solve(a, b, idx + 1, true, map);
      swap(a, b, idx);
      withoutSwap = solve(a, b, idx + 1, false, map);
      min = Math.min(withoutSwap, withSwap);
    }
    map.put(key, min);
    return min;
  }

  void swap(int[] a, int[] b, int idx) {
    int temp = a[idx];
    a[idx] = b[idx];
    b[idx] = temp;
  }

  public static void main(String args[]) {
    int[] a = new int[]{1, 3, 5, 4};
    int[] b = new int[]{1, 2, 3, 7};
    MinSwapIncr m = new MinSwapIncr();
    System.out.println(m.minSwap(a, b));
  }

}
