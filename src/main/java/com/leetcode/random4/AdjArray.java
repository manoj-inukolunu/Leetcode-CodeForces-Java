package com.leetcode.random4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AdjArray {

  public int[] restoreArray(int[][] adjacentPairs) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    HashMap<Integer, Integer> degree = new HashMap<>();
    for (int i = 0; i < adjacentPairs.length; i++) {
      int[] curr = adjacentPairs[i];
      degree.put(curr[0], degree.getOrDefault(curr[0], 0) + 1);
      degree.put(curr[1], degree.getOrDefault(curr[1], 0) + 1);
      List<Integer> list = map.getOrDefault(curr[0], new ArrayList<>());
      list.add(curr[1]);
      map.put(curr[0], list);
      list = map.getOrDefault(curr[1], new ArrayList<>());
      list.add(curr[0]);
      map.put(curr[1], list);
    }
    List<Integer> list = new ArrayList();
    int start = -1;
    for (int key : degree.keySet()) {
      if (degree.get(key) == 1) {
        start = key;
      }
    }
    System.out.println(map);
    System.out.println(start);

    dfs(start, map, list, new HashSet<>());
    return list.stream().mapToInt(num -> num).toArray();
  }

  private void dfs(int node, HashMap<Integer, List<Integer>> map, List<Integer> list, HashSet<Integer> visited) {
    if (map.containsKey(node) && !visited.contains(node)) {
      visited.add(node);
      list.add(node);
      for (int child : map.get(node)) {
        dfs(child, map, list, visited);
      }
    }
  }

  public static void main(String args[]) {
    AdjArray a = new AdjArray();
    int[][] arr = new int[][]{
        {2, 1}, {3, 4}, {3, 2}
    };
    int[][] arr1 = new int[][]{
        {4, -2}, {1, 4}, {-3, 1}
    };

    System.out.println(Arrays.toString(a.restoreArray(arr1)));
  }
}
