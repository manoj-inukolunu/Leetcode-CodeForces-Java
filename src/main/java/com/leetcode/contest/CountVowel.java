package com.leetcode.contest;

import java.util.Arrays;
import java.util.HashMap;

public class CountVowel {

  public int countVowelStrings(int n) {
    HashMap<Integer, Integer[]> map = dfs(new HashMap(), n);
    int count = 0;
    Integer[] counts = map.get(n);
    for (int i = 0; i < counts.length; i++) {
      count += counts[i];
    }
    return count;
  }

  private HashMap<Integer, Integer[]> dfs(HashMap<Integer, Integer[]> map, int n) {
    if (n == 1) {
      Integer[] integers = new Integer[5];
      Arrays.fill(integers, 1);
      map.put(1, integers);
      return map;
    }
    map = dfs(map, n - 1);
    Integer[] prev = map.get(n - 1);
    Integer[] next = new Integer[5];
    Arrays.fill(next, 0);
    for (int i = 0; i < prev.length; i++) {
      for (int j = i; j < prev.length; j++) {
        next[j] += prev[i];
      }
    }
    map.put(n, next);
    map.remove(n - 1);
    return map;
  }

  public static void main(String args[]) {
    CountVowel c = new CountVowel();
    System.out.println(c.countVowelStrings(50));
  }

}
