package com.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectCitiesWithMinCost {

  class Edge {

    int to;
    int cost;

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  public int minimumCost(int N, int[][] connections) {
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < connections.length; i++) {
      int[] curr = connections[i];
      List<Edge> edges = graph.getOrDefault(curr[0], new ArrayList<>());
      edges.add(new Edge(curr[1], curr[2]));
      graph.put(curr[0], edges);
      edges = graph.getOrDefault(curr[1], new ArrayList<>());
      edges.add(new Edge(curr[0], curr[2]));
      graph.put(curr[1], edges);
    }
    List<Edge> mst = new ArrayList<>();
    boolean[] visited = new boolean[N + 1];
    int start = 1;
    PriorityQueue<Edge> p = new PriorityQueue<>();
    for (Edge e : graph.get(start)) {
      p.add(e);
    }
    visited[start] = true;
    while (!p.isEmpty()) {
      Edge curr = p.poll();
      if (!visited[curr.to]) {
        visited[curr.to] = true;
        mst.add(curr);
        if (graph.containsKey(curr.to)) {
          for (Edge edge : graph.get(curr.to)) {
            if (!visited[edge.to]) {
              p.add(edge);
            }
          }
        }
      }
    }

    if (mst.size() == N - 1) {
      int sum = 0;
      for (int i = 0; i < mst.size(); i++) {
        sum += mst.get(i).cost;
      }

      return sum;
    }
    return -1;
  }

}
