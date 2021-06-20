package com.leetcode.random;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MaximalNetworkRank {


  public int maximalNetworkRank(int n, int[][] roads) {
    boolean[][] arr = new boolean[n][n];
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < roads.length; i++) {
      int[] road = roads[i];
      Set<Integer> set = map.getOrDefault(road[0], new HashSet<>());
      set.add(road[1]);
      map.put(road[0], set);
      set = map.getOrDefault(road[1], new HashSet<>());
      set.add(road[0]);
      map.put(road[1], set);
    }
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int count = 0;
        if (map.containsKey(i)) {
          for (int dir : map.get(i)) {
            if (!arr[i][dir]) {
              arr[i][dir] = true;
              arr[dir][i] = true;
              count++;
            }
          }
        }
        if (map.containsKey(j)) {
          for (int dir : map.get(j)) {
            if (!arr[j][dir]) {
              arr[j][dir] = true;
              arr[dir][j] = true;
              count++;
            }
          }
        }
        max = Math.max(count, max);
        arr = new boolean[n][n];
      }
    }
    return max;
  }

  public static void main(String args[]) {
    int[][] roads = new int[][]{
        {0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}
    };
    MaximalNetworkRank m = new MaximalNetworkRank();
    System.out.println(m.maximalNetworkRank(5, roads));
  }

}
