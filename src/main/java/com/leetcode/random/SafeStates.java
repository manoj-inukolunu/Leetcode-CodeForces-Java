package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SafeStates {

  public List<Integer> eventualSafeNodes(int[][] graph) {
    Set<Integer> ans = new HashSet<>();
    for (int i = 0; i < graph.length; i++) {
      if (graph[i].length == 0) {
        ans.add(i);
      }
    }
    Set<Integer> recur = new HashSet<>();
    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < graph.length; i++) {
      if (!ans.contains(i) && !recur.contains(i) && !hasCycle(i, graph, recur, visited)) {
        ans.add(i);
      }
    }
    return new ArrayList<>(ans);
  }

  private boolean hasCycle(int node, int[][] graph, Set<Integer> callStack, Set<Integer> visited) {
    if (callStack.contains(node)) {
      return true;
    }
    if (!visited.contains(node)) {
      visited.add(node);
      callStack.add(node);
      for (int child : graph[node]) {
        if (hasCycle(child, graph, callStack, visited)) {
          return true;
        }
      }
      callStack.remove(node);
      return false;
    }
    return false;
  }

  public static void main(String args[]) {
    int[][] graph = new int[][]{
        {1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}
    };
    SafeStates s = new SafeStates();
    System.out.println(s.eventualSafeNodes(graph));
  }

}
