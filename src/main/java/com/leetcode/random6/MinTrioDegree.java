package com.leetcode.random6;

import java.util.Arrays;

public class MinTrioDegree {

  public int minTrioDegree(int n, int[][] edges) {
    int[][] adj = new int[n + 1][n + 1];
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      adj[edge[0]][edge[1]] = 1;
      adj[edge[1]][edge[0]] = 1;
    }

    int ans = Integer.MAX_VALUE;
    int[] size = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      size[i] = Arrays.stream(adj[i]).sum();
    }

    for (int u = 1; u <= n; u++) {
      for (int v = 1; v <= n; v++) {
        if (adj[u][v] == 1) {
          for (int w = 1; w <= n; w++) {
            if (adj[w][u] == 1 && adj[v][w] == 1 && u != v && v != w && u != w) {
              int degree = size[u] + size[v] + size[w] - 6;
              ans = Math.min(ans, degree);
            }
          }
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    int[][] graph = new int[][]{{1, 2}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {3, 6}};
    MinTrioDegree m = new MinTrioDegree();
    System.out.println(m.minTrioDegree(7, graph));
  }

}
