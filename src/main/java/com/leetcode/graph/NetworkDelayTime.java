package com.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

  class Edge {

    int dest;
    int weight;

    public Edge(int dest, int weight) {
      this.dest = dest;
      this.weight = weight;
    }
  }


  private int[] dijkstra(HashMap<Integer, List<Edge>> graph, int numNodes, int start) {
    boolean[] visited = new boolean[numNodes];
    Arrays.fill(visited, false);
    int[] dist = new int[numNodes];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[start] = 0;
    PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
    priorityQueue.add(new Edge(start, 0));
    while (!priorityQueue.isEmpty()) {
      Edge curr = priorityQueue.poll();
      visited[curr.dest] = true;
      if (dist[curr.dest] < curr.weight) {
        continue;
      }
      for (Edge edge : graph.get(curr.dest)) {
        if (!visited[edge.dest]) {
          int newDist = dist[curr.dest] + edge.weight;
          if (newDist < dist[edge.dest]) {
            dist[edge.dest] = newDist;
            priorityQueue.add(new Edge(edge.dest, newDist));
          }
        }
      }
    }
    return dist;
  }

  public int networkDelayTime(int[][] times, int N, int K) {
    HashMap<Integer, List<Edge>> graph = new HashMap<>();
    for (int[] edge : times) {
      List<Edge> list = graph.getOrDefault(edge[0], new ArrayList<>());
      list.add(new Edge(edge[1], edge[2]));
      graph.put(edge[0], list);
    }
    int[] dist = dijkstra(graph, N, K);
    int total = 0;
    int max = 0;
    for (int i = 0; i < dist.length; i++) {
      if (dist[i] != Integer.MAX_VALUE) {
        total += dist[i];
        max = Math.max(dist[i],max);
      } else {
        return -1;
      }
    }
    return max;
  }

}
