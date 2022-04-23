package com.leetcode.hard;

import com.leetcode.common.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Solution {

  public Set<Integer> nodesInCycle = new HashSet<>();

  public int[] distanceToCycle(int n, int[][] edges) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int[] edge : edges) {
      Set<Integer> set = map.getOrDefault(edge[0], new HashSet<>());
      set.add(edge[1]);
      map.put(edge[0], set);
      set = map.getOrDefault(edge[1], new HashSet<>());
      set.add(edge[0]);
      map.put(edge[1], set);
    }
    dfs(map, 0, -1, new HashSet<>(), new Stack<>());
    Queue<Integer[]> queue = new LinkedList<>();
    int[] ans = new int[n];
    for (int node : nodesInCycle) {
      queue.add(new Integer[] {node, 0});
      ans[node] = 0;
    }
    System.out.println(nodesInCycle);
    Set<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Integer[] current = queue.poll();
      if (!visited.contains(current[0])) {
        visited.add(current[0]);
        ans[current[0]] = current[1];
        for (int child : map.getOrDefault(current[0], new HashSet<>())) {
          queue.add(new Integer[] {child, current[1] + 1});
        }
      }
    }
    return ans;
  }

  public void dfs(Map<Integer, Set<Integer>> graph, int node, int parent, Set<Integer> visited, Stack<Integer> stack) {
    visited.add(node);
    stack.push(node);
    for (int child : graph.getOrDefault(node, new HashSet<>())) {
      if (node != child && child != parent) {
        if (visited.contains(child)) {
          if (!nodesInCycle.isEmpty()) {
            return;
          }
          System.out.println("Stack : " + child + " " + stack);
          //cycle found
          while (!stack.isEmpty() && stack.peek() != child) {
            nodesInCycle.add(stack.pop());
          }
          if (!stack.isEmpty()) {
            nodesInCycle.add(stack.pop());
          }
          return;
        } else {
          dfs(graph, child, node, visited, stack);
        }
      }
    }
    visited.remove(node);
    if (!stack.isEmpty()) {
      stack.pop();
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();

    System.out.println(Arrays.toString(
        s.distanceToCycle(8, Utils.convertToTwoDIntArray("[[4,0],[7,5],[1,2],[3,2],[5,2],[7,0],[3,6],[6,1]]"))));
  }
}
