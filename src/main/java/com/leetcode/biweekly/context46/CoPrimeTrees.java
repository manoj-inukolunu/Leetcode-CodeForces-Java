package com.leetcode.biweekly.context46;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoPrimeTrees {

  private int gcd(int a, int b) {
    if (a == 0) {
      return b;
    }
    if (b == 0) {
      return a;
    }
    if (a == b) {
      return a;
    }
    return a > b ? gcd(a - b, b) : gcd(a, b - a);
  }

  int[] ans;
  int[][] gc = new int[51][51];
  int[] num;

  public int[] getCoprimes(int[] nums, int[][] edges) {

    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
      list.add(edge[1]);
      map.put(edge[0], list);
    }
    ans = new int[nums.length];
    num = nums;
    for (int i = 1; i <= 50; i++) {
      for (int j = 1; j <= 50; j++) {
        gc[i][j] = gcd(i, j);
      }
    }
    dfs(0, 0, map, new HashMap<>(), new HashSet<>());
    return ans;
  }

  private void dfs(int node, int depth, HashMap<Integer, List<Integer>> map, HashMap<Integer, List<Integer[]>> path, Set<Integer> visited) {
    if (!visited.contains(node)) {
      int maxDepth = Integer.MAX_VALUE;
      int val = -1;
      for (Integer key : path.keySet()) {
        if (gc[num[node]][key] == 1) {
          List<Integer[]> list = path.get(key);
          if (list != null && !list.isEmpty()) {
            Integer[] currNodeDepth = list.get(list.size() - 1);
            if (currNodeDepth[0] < maxDepth) {
              maxDepth = currNodeDepth[0];
              val = currNodeDepth[1];
            }
          }
        }
      }
      ans[node] = val;
      visited.add(node);
      List<Integer[]> list = path.getOrDefault(num[node], new ArrayList<>());
      list.add(new Integer[]{depth, node});
      path.put(num[node], list);
      if (map.containsKey(node)) {
        for (int child : map.get(node)) {
          dfs(child, depth + 1, map, path, visited);
        }
      }
      path.get(num[node]).remove(path.get(num[node]).size() - 1);
      if (path.get(num[node]).isEmpty()) {
        path.remove(num[node]);
      }
    }
  }

  public static void main(String args[]) {
    int[][] edges = new int[][]{
        {0, 1}, {1, 2}, {1, 3}
    };
    int[] nums = new int[]{2, 3, 3, 2};
    CoPrimeTrees c = new CoPrimeTrees();
    System.out.println(Arrays.toString(c.getCoprimes(nums, edges)));
  }
}
