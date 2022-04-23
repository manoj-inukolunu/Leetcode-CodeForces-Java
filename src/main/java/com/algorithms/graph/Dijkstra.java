package com.algorithms.graph;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

  private int[] dijkstra(int source, HashMap<Integer, Set<Integer>> graph, int[][] weights, int n) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE / 2);
    dist[source] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
    pq.add(new int[] {source, 0});
    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int vertex = curr[0];
      int vertexDist = curr[1];
      if (dist[vertex] != vertexDist) {
        continue;
      }
      for (int next : graph.getOrDefault(vertex, new HashSet<>())) {
        int len = weights[vertex][next];
        if (dist[vertex] + len < dist[next]) {
          dist[next] = dist[vertex] + len;
          pq.add(new int[] {next, dist[next]});
        }
      }
    }
    return dist;
  }
}
