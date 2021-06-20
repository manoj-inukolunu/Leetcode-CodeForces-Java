package com.leetcode.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class ShortestPathWithAlternatingColors {

  class Edge {

    int node;
    int color;
    int len;

    public Edge(int node, int color, int len) {
      this.node = node;
      this.color = color;
      this.len = len;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Edge edge = (Edge) o;
      return node == edge.node &&
          color == edge.color;
    }

    @Override
    public int hashCode() {
      return Objects.hash(node, color);
    }
  }

  public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    HashMap<Integer, Set<Edge>> graph = new HashMap<>();
    buildGraph(red_edges, graph, 0);
    buildGraph(blue_edges, graph, 1);
    int[] ans = new int[n];
    Arrays.fill(ans, Integer.MAX_VALUE);
    Queue<Edge> queue = new LinkedList<>();
    queue.add(new Edge(0, -1, 0));
    HashSet<Edge> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Edge curr = queue.poll();
      ans[curr.node] = Math.min(ans[curr.node], curr.len);
      if (!visited.contains(curr)) {
        visited.add(curr);
        if (graph.containsKey(curr.node)) {
          for (Edge next : graph.get(curr.node)) {
            if (next.color != curr.color) {
              queue.add(new Edge(next.node, next.color, curr.len + 1));
            }
          }
        }
      }
    }
    for (int i = 0; i < ans.length; i++) {
      if (ans[i] == Integer.MAX_VALUE) {
        ans[i] = -1;
      }
    }
    return ans;
  }

  private void buildGraph(int[][] edges, HashMap<Integer, Set<Edge>> graph, int color) {
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      Set<Edge> set = graph.getOrDefault(edge[0], new HashSet<>());
      set.add(new Edge(edge[1], color, 0));
      graph.put(edge[0], set);
    }
  }

  public static void main(String args[]) {
    ShortestPathWithAlternatingColors s = new ShortestPathWithAlternatingColors();
    int[][] red = new int[][]{
        {0, 1}, {0, 2}
    };
    int[][] blue = new int[][]{
        {1, 0}
    };
    int[] ans = s.shortestAlternatingPaths(3, red, blue);
    System.out.println(Arrays.toString(ans));
  }


}
