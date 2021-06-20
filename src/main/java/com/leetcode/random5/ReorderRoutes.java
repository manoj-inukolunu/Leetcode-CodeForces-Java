package com.leetcode.random5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ReorderRoutes {

  int count = 0;

  public int minReorder(int n, int[][] connections) {
    HashMap<Integer, List<Integer>> fMap = new HashMap<>();
    HashMap<Integer, List<Integer>> bMap = new HashMap<>();
    for (int[] conn : connections) {
      List<Integer> l = fMap.getOrDefault(conn[0], new ArrayList<>());
      l.add(conn[1]);
      fMap.put(conn[0], l);
      l = bMap.getOrDefault(conn[1], new ArrayList<>());
      l.add(conn[0]);
      bMap.put(conn[1], l);
    }

    System.out.println(fMap);
    dfs(0, fMap, bMap, new HashSet<>());

    return count;
  }

  private void dfs(int node, HashMap<Integer, List<Integer>> fMap, HashMap<Integer, List<Integer>> bMap, HashSet<Integer> visited) {
    if (!visited.contains(node)) {
      visited.add(node);
      if (fMap.containsKey(node)) {
        for (int child : fMap.get(node)) {
          if (!visited.contains(child)) {
            count++;
            dfs(child, fMap, bMap, visited);
          }
        }
      }
      if (bMap.containsKey(node)) {
        for (int child : bMap.get(node)) {
          if (!visited.contains(child)) {
            dfs(child, fMap, bMap, visited);
          }
        }
      }
    }
  }

  public static void main(String args[]) {
    ReorderRoutes r = new ReorderRoutes();
    int[][] arr = new int[][]{
        {0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}
    };

    System.out.println(r.minReorder(6, arr));
  }

}
