package com.leetcode.Tree.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CountSubTreesWithSameLabel {

  HashMap<Integer, List<Integer>> tree = new HashMap<>();

  public int[] countSubTrees(int n, int[][] edges, String labels) {
    int[] parent = new int[n];
    parent[0] = -1;

    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      List<Integer> list = tree.getOrDefault(edge[0], new ArrayList<>());
      list.add(edge[1]);
      tree.put(edge[0], list);
      list = tree.getOrDefault(edge[1], new ArrayList<>());
      list.add(edge[0]);
      tree.put(edge[1], list);
    }
    boolean[] visited = new boolean[n];
    int[] label = new int[n];
    Arrays.fill(label, 1);
    dfs(0, labels, label, visited);
    return label;
  }

  private HashMap<Character, Integer> dfs(int curr, String labels, int[] label, boolean[] visited) {
    visited[curr] = true;
    if (tree.get(curr) != null) {
      HashMap<Character, Integer> tempMap = new HashMap<>();
      tempMap.put(labels.charAt(curr), 1);
      for (int child : tree.get(curr)) {
        if (!visited[child]) {
          HashMap<Character, Integer> childMap = dfs(child, labels, label, visited);
          if (childMap.containsKey(labels.charAt(curr))) {
            int count = childMap.get(labels.charAt(curr));
            label[curr] += count;
          }
          for (Character key : childMap.keySet()) {
            tempMap.put(key, tempMap.getOrDefault(key, 0) + childMap.get(key));
          }
        }
      }
      return tempMap;
    } else {
      HashMap<Character, Integer> map = new HashMap<>();
      map.put(labels.charAt(curr), 1);
      return map;
    }
  }

  public int smallestRepunitDivByK(int K) {
    Long curr = 1L;
    while (curr < Long.MAX_VALUE) {
      if (curr % K == 0) {
        return String.valueOf(curr).length();
      }
      curr = Long.parseLong(curr + "1");
    }

    return -1;
  }

  public static void main(String args[]) {
    CountSubTreesWithSameLabel c = new CountSubTreesWithSameLabel();
    int[][] edges = {
        {0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}
    };
    System.out.println(c.smallestRepunitDivByK(1123));
  }

}
