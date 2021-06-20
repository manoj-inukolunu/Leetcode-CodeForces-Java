package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumHeightTrees {

  int max = Integer.MIN_VALUE;
  int node1 = -1;

  HashMap<Integer, List<Integer>> map = new HashMap();
  List<Integer> temp = new ArrayList<>();

  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      List<Integer> list = map.getOrDefault(edge[0], new ArrayList());
      list.add(edge[1]);
      map.put(edge[0], list);
      list = map.getOrDefault(edge[1], new ArrayList());
      list.add(edge[0]);
      map.put(edge[1], list);
    }
    boolean[] visited = new boolean[n];
    dfs(0, 1, visited);
    visited = new boolean[n];
    max = Integer.MIN_VALUE;
    List<Integer> ans = new ArrayList<>();
    dfs(node1, 1, visited, ans);
    if (temp.size() % 2 != 0) {
      ans.add(temp.get(temp.size() / 2));
    } else {
      ans.add(temp.get(temp.size() / 2));
      ans.add(temp.get((temp.size() / 2) - 1));
    }
    return ans;
  }

  private void dfs(Integer curr, int depth, boolean[] visited, List<Integer> ans) {
    if (!visited[curr]) {
      visited[curr] = true;
      ans.add(curr);
      boolean leaf = true;
      if (map.containsKey(curr)) {
        for (int child : map.get(curr)) {
          if (!visited[child]) {
            leaf = false;
            dfs(child, depth + 1, visited, ans);
          }
        }
      }
      if (leaf && depth >= max) {
        max = depth;
        temp = new ArrayList<>(ans);
      }
      ans.remove(curr);
    }
  }

  private void dfs(Integer curr, Integer depth, boolean[] visited) {
    if (!visited[curr]) {
      visited[curr] = true;
      boolean leaf = true;
      if (map.containsKey(curr)) {
        for (int child : map.get(curr)) {
          if (!visited[child]) {
            leaf = false;
            dfs(child, depth + 1, visited);
          }
        }
      }
      if (leaf && depth >= max) {
        max = depth;
        node1 = curr;
      }
    }
  }

  public static void main(String args[]) {
    MinimumHeightTrees m = new MinimumHeightTrees();
    int[][] arr = new int[][]{
        {3, 0},
        {3, 1},
        {3, 2},
        {3, 4},
        {5, 4},
    };
    System.out.println(m.findMinHeightTrees(6, arr));
  }

}
