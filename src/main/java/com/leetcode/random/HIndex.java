package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class HIndex {

  public int hIndex(int[] arr) {
    Arrays.sort(arr);
    TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
    for (int i = 0; i < arr.length; i++) {
      ArrayList<Integer> set = map.getOrDefault(arr[i], new ArrayList<>());
      set.add(i);
      map.put(arr[i], set);
    }

    int len = arr.length;

    for (int i = len; i >= 1; i--) {
      if (isValid(i, map, len)) {
        return i;
      }
    }
    return -1;
  }

  private boolean isValid(int hIndex, TreeMap<Integer, ArrayList<Integer>> map, int total) {
    if (map.containsKey(hIndex)) {
      int idx = map.get(hIndex).get(0);
      if (check(hIndex, total, idx)) {
        return true;
      }
      List<Integer> counts = map.get(hIndex);
      idx = counts.get(counts.size() - 1);
      if (check(hIndex, total, idx)) {
        return true;
      }
    } else {
      Integer higherKey = map.higherKey(hIndex);
      if (higherKey == null) {
        return false;
      } else {
        int idx = map.get(higherKey).get(0);
        if (check(hIndex, total, idx)) {
          return true;
        }
        List<Integer> counts = map.get(higherKey);
        idx = counts.get(counts.size() - 1);
        if (check(hIndex, total, idx)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean check(int hIndex, int total, int idx) {
    if (total - idx + 1 >= hIndex) {
      int smaller = idx;
      if (smaller == 0 || smaller == total - hIndex) {
        return true;
      }
    }
    return false;
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2};
    HIndex h = new HIndex();
    System.out.println(h.hIndex(arr));
  }


}
