package com.leetcode.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GraphValidTree {

  public boolean validTree(int n, int[][] edges) {

    HashMap<Integer, List<Integer>> map = new HashMap<>();
    boolean visited[] = new boolean[n];
    Arrays.fill(visited, false);

    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
      list.add(edge[1]);
      map.put(edge[0], list);
      list = map.getOrDefault(edge[1], new ArrayList<>());
      list.add(edge[0]);
      map.put(edge[1], list);
    }

    boolean isTree = dfs(0, map, visited, -1);
    for (int i = 0; i < visited.length; i++) {
      if (!visited[i]) {
        isTree = false;
        break;
      }
    }

    return isTree;
  }

  private boolean dfs(int curr, HashMap<Integer, List<Integer>> map, boolean[] visited, int parent) {
    visited[curr] = true;
    for (int i : map.get(curr)) {
      if (!visited[i]) {
        if (!dfs(i, map, visited, curr)) {
          return false;
        }
      } else if (i != parent) {
        return false;
      }
    }
    return true;
  }

}
