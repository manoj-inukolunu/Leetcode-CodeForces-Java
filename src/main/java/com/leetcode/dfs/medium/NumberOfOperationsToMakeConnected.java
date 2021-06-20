package com.leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfOperationsToMakeConnected {


  int numCables = -1;

  public int makeConnected(int n, int[][] connections) {

    HashMap<Integer, Set<Integer>> graph = new HashMap<>();
    for (int i = 0; i < connections.length; i++) {
      int[] conn = connections[i];
      Set<Integer> list = graph.getOrDefault(conn[0], new HashSet<>());
      list.add(conn[1]);
      graph.put(conn[0], list);
      list = graph.getOrDefault(conn[1], new HashSet<>());
      list.add(conn[0]);
      graph.put(conn[1], list);
    }
    boolean visited[] = new boolean[n];
    int numComponents = 0;
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(i, -1, graph, visited);
        numComponents++;
      }
    }
    System.out.println(numCables);
    return numCables >= (numComponents - 1) ? (numComponents - 1) : -1;
  }

  private void dfs(int node, int prev, HashMap<Integer, Set<Integer>> graph, boolean[] visited) {
    visited[node] = true;
    if (graph.containsKey(node)) {
      for (int child : graph.get(node)) {
        if (child == node) {
          continue;
        }
        if (child != prev) {
          if (visited[child] && prev != -1) {
            numCables++;
          } else {
            dfs(child, node, graph, visited);
          }
        }
      }
    }
  }

  public static void main(String args[]) {
    NumberOfOperationsToMakeConnected n = new NumberOfOperationsToMakeConnected();
    int[][] connections = new int[][]{
        {0, 1}, {0, 2}, {1, 2}
    };
    int numNodes = 4;
    System.out.println(n.makeConnected(numNodes, connections));
  }

}
