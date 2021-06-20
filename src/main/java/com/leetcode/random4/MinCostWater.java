package com.leetcode.random4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class MinCostWater {


  class Edge {

    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }

    @Override
    public String toString() {
      return "Edge{" +
          "start=" + from +
          ", end=" + to +
          ", cost=" + cost +
          '}';
    }
  }

  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    int min = 0;
    HashMap<Integer, List<Edge>> map = new HashMap<>();
    for (int i = 0; i < pipes.length; i++) {
      List<Edge> list = map.getOrDefault(pipes[i][0], new ArrayList<>());
      list.add(new Edge(pipes[i][0], pipes[i][1], pipes[i][2]));
      map.put(pipes[i][0], list);
      list = map.getOrDefault(pipes[i][1], new ArrayList<>());
      list.add(new Edge(pipes[i][1], pipes[i][0], pipes[i][2]));
      map.put(pipes[i][1], list);
    }
    map.put(0, new ArrayList<>());
    for (int i = 0; i < wells.length; i++) {
      map.get(0).add(new Edge(0, i + 1, wells[i]));
    }
    PriorityQueue<Edge> p = new PriorityQueue<>(Comparator.comparing(edge -> edge.cost));
    HashSet<Integer> set = new HashSet<>();
    p.addAll(map.get(0));
    set.add(0);
    while (!p.isEmpty() && set.size() < n + 1) {
      Edge curr = p.poll();
      if (!set.contains(curr.to)) {
        min += curr.cost;
        if (map.containsKey(curr.to)) {
          for (Edge next : map.get(curr.to)) {
            if (!set.contains(next.to)) {
              p.add(next);
            }
          }
          set.add(curr.to);
        }

      }
    }
    return min;
  }


  public static void main(String args[]) {
    int[][] pipes = new int[][]{
        {2, 1, 6719}, {3, 2, 75312}, {5, 3, 44918}
    };
    int[] wells = new int[]{46012, 72474, 64965, 751, 33304};
    MinCostWater m = new MinCostWater();
    System.out.println(m.minCostToSupplyWater(5, wells, pipes));
  }
}
