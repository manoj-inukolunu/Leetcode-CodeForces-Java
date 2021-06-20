package com.leetcode.random;

import com.leetcode.graph.AllPathsFromSrcToTarget;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AllPathsToDest {

  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      int[] curr = edges[i];
      Set<Integer> set = map.getOrDefault(curr[0], new HashSet<>());
      set.add(curr[1]);
      map.put(curr[0], set);
    }
    if (map.get(destination) != null) {
      return false;
    }
    if (isCyclic(source, n, map)) {
      return false;
    }
    boolean[] visited = new boolean[n + 1];
    return dfs(source, destination, map, visited);
  }

  private boolean dfs(int curr, int destination, HashMap<Integer, Set<Integer>> map, boolean[] visited) {
    visited[curr] = true;
    if (curr == destination && map.get(destination) == null) {
      return true;
    }
    if (map.containsKey(curr)) {
      boolean ret = true;
      for (int next : map.get(curr)) {
        ret = ret && dfs(next, destination, map, visited);
      }
      return ret;
    } else {
      return false;
    }
  }
  private boolean isCyclic(int from, int V, HashMap<Integer, Set<Integer>> map) {
    boolean[] visited = new boolean[V];
    boolean[] recStack = new boolean[V];
    if (isCyclicUtil(from, visited, recStack, map)) {
      return true;
    }
    return false;
  }

  private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack, HashMap<Integer, Set<Integer>> map) {
    if (recStack[i]) {
      return true;
    }
    if (visited[i]) {
      return false;
    }
    visited[i] = true;
    recStack[i] = true;
    Set<Integer> children = map.get(i);
    if (children != null) {
      for (Integer c : children) {
        if (isCyclicUtil(c, visited, recStack, map)) {
          return true;
        }
      }
    } else {
      return false;
    }
    recStack[i] = false;
    return false;
  }

  public static void main(String args[]) {
    AllPathsToDest a = new AllPathsToDest();
    int[][] edges = new int[][]{
        {0, 1}, {1, 1}
    };
    System.out.println(a.leadsToDestination(2, edges, 0, 1));
  }

}
