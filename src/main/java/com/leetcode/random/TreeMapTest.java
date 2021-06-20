package com.leetcode.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapTest {

  class Pair {

    int index;
    int num;

    public Pair(int index, int num) {
      this.index = index;
      this.num = num;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return index == pair.index &&
          num == pair.num;
    }

    @Override
    public int hashCode() {
      return Objects.hash(index, num);
    }
  }

  public int test(int[] v) {
    int[] maxFromEnd = new int[v.length + 1];
    Arrays.fill(maxFromEnd, Integer.MIN_VALUE);
    for (int i = v.length - 1; i >= 0; i--) {
      maxFromEnd[i] = Math.max(maxFromEnd[i + 1], v[i]);
    }
    System.out.println(Arrays.toString(maxFromEnd));
    int result = 0;
    for (int i = 0; i < v.length; i++) {
      int low = i + 1, high = v.length - 1, ans = i;
      while (low <= high) {
        int mid = (low + high) / 2;
        if (v[i] <= maxFromEnd[mid]) {
          ans = Math.max(ans, mid);
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      result = Math.max(result, ans - i);
    }
    return result;
  }


  public int maxWidthRamp(int[] arr) {
    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
    for (int i = 0; i < arr.length; i++) {
      TreeSet<Integer> set = map.getOrDefault(arr[i], new TreeSet<>(Collections.reverseOrder()));
      set.add(i);
      map.put(arr[i], set);
    }
    TreeMap<Integer, Integer> tMap = new TreeMap<>();
    for (int key : map.keySet()) {
      tMap.put(key, map.get(key).first());
    }
    int max = 0;
    for (int i = 0; i < arr.length; i++) {
      Integer next;
      if (map.containsKey(arr[i])) {
        next = arr[i];
      } else {
        next = map.higherKey(arr[i]);
      }
      while (next != null) {
        int j = tMap.get(next);
        if (j > i) {
          max = Math.max(max, j - i);
        }
        next = map.higherKey(next);
      }
    }
    return max;
  }

  public static void main(String args[]) {
    TreeMapTest t = new TreeMapTest();
    int[] arr = new int[]{6, 0, 8, 2, 1, 5}; // 0 1 2 5 6 8
    System.out.println(t.test(arr));
  }
}
