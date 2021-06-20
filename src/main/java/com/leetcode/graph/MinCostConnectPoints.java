package com.leetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostConnectPoints {

  class Edge {

    int to;
    int cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  public int minCostConnectPoints(int[][] points) {

    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < points.length; i++) {
      for (int j = 0; j < points.length; j++) {
        if (i != j) {
          int[] src = points[i];
          int[] dst = points[j];
          List<Edge> edges = graph.getOrDefault(i, new ArrayList<>());
          edges.add(new Edge(j, Math.abs(src[0] - dst[0]) + Math.abs(src[1] - dst[1])));
          graph.put(i, edges);
          edges = graph.getOrDefault(j, new ArrayList<>());
          edges.add(new Edge(i, Math.abs(src[0] - dst[0]) + Math.abs(src[1] - dst[1])));
          graph.put(j, edges);
        }
      }
    }
    boolean[] visited = new boolean[points.length + 1];
    int start = 0;
    PriorityQueue<Edge> p = new PriorityQueue<>(Comparator.comparing(edge -> edge.cost));
    p.addAll(graph.get(start));
    visited[start] = true;
    int sum = 0;
    while (!p.isEmpty()) {
      Edge curr = p.poll();
      if (!visited[curr.to]) {
        sum += curr.cost;
        for (Edge edge : graph.get(curr.to)) {
          if (!visited[edge.to]) {
            p.add(edge);
          }
        }
      }
    }
    return sum;
  }

}
