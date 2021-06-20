package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author manoji on 8/7/20.
 */
public class TreeDiameter {

  HashMap<Integer, List<Integer>> map = new HashMap<>();


  class Pair {

    int node;
    int dist;

    public Pair(int node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }

  public int treeDiameter(int[][] edges) {

    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      updateMap(edge[0], edge[1]);
      updateMap(edge[1], edge[0]);
    }

    Queue<Pair> queue = new LinkedList<>();
    HashSet<Integer> visited = new HashSet<>();
    queue.add(new Pair(0, 0));
    int max1 = Integer.MIN_VALUE, node1 = -1;
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.dist > max1) {
        max1 = curr.dist;
        node1 = curr.node;
      }
      if (!visited.contains(curr.node)) {
        visited.add(curr.node);
        for (Integer child : map.get(curr.node)) {
          queue.add(new Pair(child, curr.dist + 1));
        }
      }
    }

    queue.clear();
    visited.clear();

    queue.add(new Pair(node1, 0));
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.dist > max1) {
        max1 = curr.dist;
        node1 = curr.node;
      }
      if (!visited.contains(curr.node)) {
        visited.add(curr.node);
        for (Integer child : map.get(curr.node)) {
          queue.add(new Pair(child, curr.dist + 1));
        }
      }
    }
    return max1;
  }

  private void updateMap(int v1, int v2) {
    List<Integer> adjList = map.getOrDefault(v1, new ArrayList<>());
    adjList.add(v2);
    map.put(v1, adjList);
  }

}
