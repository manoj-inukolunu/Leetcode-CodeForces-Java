package com.leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.checkerframework.checker.units.qual.A;

public class AllPathsFromSrcToTarget {


  List<List<Integer>> ans = new ArrayList<>();

  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    dfs(0, graph, new HashSet<>(), new boolean[graph.length]);
    return ans;
  }

  private void dfs(Integer node, int[][] graph, Set<Integer> collector, boolean[] visited) {
    collector.add(node);
    if (node == graph.length - 1) {
      ans.add(new ArrayList<>(collector));
      collector.remove(node);
      return;
    }
    for (int child : graph[node]) {
      dfs(child, graph, collector, visited);
    }
    collector.remove(node);
  }

  public static void main(String args[]) {
    int[][] graph = new int[][]{
        {4, 3, 1}, {3, 2, 4}, {3}, {4}, {}
    };
    AllPathsFromSrcToTarget a = new AllPathsFromSrcToTarget();
    System.out.println(a.allPathsSourceTarget(graph));
  }

}
