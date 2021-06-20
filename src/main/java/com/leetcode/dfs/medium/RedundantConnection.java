package com.leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author manoji on 5/11/20.
 */
public class RedundantConnection {

  private void cycle(int node1, int node2, boolean[] visited, HashMap<Integer, List<Integer>> map) {
    visited[node1] = true;
    for (int child : map.get(node1)) {
      if (!visited[child]) {
        visited[child] = true;
        cycle(child, node1, visited, map);
      } else if (node1 != node2) {
        //hasCycle = true;
        return;
      }
    }
  }

  private void buildMap(int[][] connections, HashMap<Integer, Set<Integer>> map) {
    for (int i = 0; i < connections.length; i++) {
      int[] connection = connections[i];
      int node1 = connection[0];
      int node2 = connection[1];
      Set<Integer> adj1 = map.getOrDefault(node1, new HashSet<>());
      adj1.add(node2);
      map.put(node1, adj1);
      Set<Integer> adj2 = map.getOrDefault(node2, new HashSet<>());
      adj2.add(node1);
      map.put(node2, adj2);
    }
  }


  public int[] findRedundantConnection(int[][] edges) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    buildMap(edges, map);
    boolean[] visited = new boolean[map.size() + 1];
    for (int i = edges.length - 1; i - 1 >= 0; i--) {
      map.get(edges[i][0]).remove(new Integer(edges[i][1]));
      map.get(edges[i][1]).remove(new Integer(edges[i][0]));
      boolean edge0 = dfs(edges[i][0], -1, map, visited);
      Arrays.fill(visited, false);
      boolean edge1 = dfs(edges[i][1], -1, map, visited);
      Arrays.fill(visited, false);
      if (!edge0 && !edge1) {
        return edges[i];
      }
      map.get(edges[i][0]).add(edges[i][1]);
      map.get(edges[i][1]).add(edges[i][0]);
    }
    return new int[]{};
  }


  private boolean dfs(int node, int parent, HashMap<Integer, Set<Integer>> map, boolean[] visited) {
    visited[node] = true;
    boolean ret = false;
    for (int child : map.get(node)) {
      if (child == parent) {
        continue;
      }
      if (visited[child]) {
        return true;
      } else {
        if (dfs(child, node, map, visited)) {
          ret = true;
        }
      }
    }
    return ret;
  }

  public static void main(String args[]) {
    RedundantConnection r = new RedundantConnection();
		/*
		[[20,24],[3,17],[17,20],[8,15],[14,17],[6,17],[15,23],[6,8],[15,19],[16,22],[7,9],[8,22],[2,4],[4,11],[22,25],[6,24],[13,19],[15,18],[1,9],[4,
		9],[4,19],[5,10],[4,21],[4,12],[5,6]]
		 */

    //[[1,3],[3,4],[1,5],[3,5],[2,3]]
    //[[1,4],[3,4],[1,3],[1,2],[4,5]]
    //[[1,4],[3,4],[1,3],[1,2],[4,5]]
    int[][] arr = new int[][]{
        {20, 24},
        {3, 17},
        {17, 20},
        {8, 15},
        {14, 17},
        {6, 17},
        {15, 23},
        {6, 8},
        {15, 19},
        {16, 22},
        {7, 9},
        {8, 22},
        {2, 4},
        {4, 11},
        {22, 25},
        {6, 24},
        {13, 19},
        {15, 19},
        {15, 18},
        {1, 9},
        {4, 9},
        {4, 19},
        {5, 10},
        {4, 21},
        {4, 12},
        {5, 6}
    };

    System.out.println(Arrays.toString(r.findRedundantConnection(arr)));
  }

}
