package com.leetcode.random;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class ClosestCities {

  
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] graph = new int[n][n];
    for (int[] row : graph) {
      Arrays.fill(row, 100000);
    }
    int[][] dist;
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      graph[edge[0]][edge[1]] = edge[2];
      graph[edge[1]][edge[0]] = edge[2];
    }
    dist = floydWarshall(graph, n);
    TreeMap<Integer, Integer> map = new TreeMap<>();
    int maxCity = Integer.MIN_VALUE;
    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        if (i != j && dist[i][j] <= distanceThreshold) {
          int count = map.getOrDefault(i, 0);
          map.put(i, count + 1);
        }
      }
    }
    int minVal = Collections.min(map.values());
    for (int key : map.keySet()) {
      if (map.get(key) == minVal) {
        maxCity = key;
      }
    }
    for (int i = 0; i < n; i++) {
      if (!map.containsKey(i)) {
        maxCity = i;
      }
    }
    return maxCity;
  }

  int[][] floydWarshall(int graph[][], int V) {
    int dist[][] = new int[V][V];
    int i, j, k;
    for (i = 0; i < V; i++) {
      for (j = 0; j < V; j++) {
        dist[i][j] = graph[i][j];
      }
    }
    for (k = 0; k < V; k++) {
      for (i = 0; i < V; i++) {
        for (j = 0; j < V; j++) {
          if (dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }
    return dist;
  }


  public static void main(String args[]) {
    ClosestCities c = new ClosestCities();
    int[][] edges = new int[][]{
        {0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}
    };
    System.out.println(c.findTheCity(5, edges, 2));
  }

}
