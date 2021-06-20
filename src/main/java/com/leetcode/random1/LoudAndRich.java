package com.leetcode.random1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LoudAndRich {


  public int[] loudAndRich(int[][] richer, int[] quiet) {

    HashMap<Integer, Set<Integer>> map = new HashMap();
    for (int i = 0; i < richer.length; i++) {
      int[] curr = richer[i];
      Set<Integer> children = map.getOrDefault(curr[1], new HashSet<>());
      children.add(curr[0]);
      map.put(curr[1], children);
    }
    int[] ans = new int[quiet.length];
    HashSet<Integer> visited = new HashSet();
    for (int i = 0; i < quiet.length; i++) {
      if (!visited.contains(i)) {
        ans[i] = dfs(i, map, quiet, visited);
      }
    }
    return ans;
  }

  private int dfs(int node, HashMap<Integer, Set<Integer>> map, int[] quiet, HashSet<Integer> visited) {
    visited.add(node);
    if (map.containsKey(node)) {
      Set<Integer> children = map.get(node);
      int min = Integer.MAX_VALUE;
      for (int child : children) {
        min = Math.min(min, dfs(child, map, quiet, visited));
      }
      return min;
    } else {
      return quiet[node];
    }
  }
}
