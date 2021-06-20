package com.leetcode.random;

import java.util.TreeMap;

public class MinIncrementForUnique {

  public int minIncrementForUnique(int[] A) {
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int num : A) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    int min = 0;
    Integer first = map.firstKey();
    while (first != null) {
      int count = map.get(first);
      if (count > 1) {
        min += (count - 1);
        map.put(first, 1);
        map.put(first + 1, map.getOrDefault(first + 1, 0) + (count - 1));
      }
      first = map.higherKey(first);
    }
    return min;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 2};
    MinIncrementForUnique m = new MinIncrementForUnique();
    System.out.println(m.minIncrementForUnique(arr));
  }

}
