package com.leetcode.dfs.medium;

import java.util.HashMap;
import java.util.List;

/**
 * @author manoji on 2020-01-11.
 */
public class Bipartite {

  public boolean isBipartite(int[][] graph) {
    int numVertices = graph.length;
    boolean[] color = new boolean[numVertices];
    boolean visited[] = new boolean[numVertices];
    for (int i = 0; i < graph.length; i++) {
      color[i] = true;
      if (!visited[i]) {
        if (!isBipartite(graph, i, visited, color)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isBipartite(int[][] graph, int vertex, boolean[] visited, boolean[] color) {
    for (int child : graph[vertex]) {
      if (!visited[child]) {
        visited[child] = true;
        color[child] = !color[vertex];
        if (!isBipartite(graph, child, visited, color)) {
          return false;
        }
      } else if (color[child] == color[vertex]) {
        return false;
      }
    }
    return true;
  }

}
