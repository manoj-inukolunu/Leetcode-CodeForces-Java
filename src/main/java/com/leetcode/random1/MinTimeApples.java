package com.leetcode.random1;

import com.google.common.collect.Lists;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinTimeApples {

  int ans = 0;

  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    HashMap<Integer, Set<Integer>> cloneMap = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      int[] curr = edges[i];
      updateMap(map, curr);
      updateMap(cloneMap, curr);

    }
    dfs(0, map, cloneMap, new HashSet<>(), hasApple);

    euler(0, cloneMap, new HashSet<>());
    return ans - 1;

  }

  private void updateMap(HashMap<Integer, Set<Integer>> map, int[] curr) {
    Set<Integer> set = map.getOrDefault(curr[0], new HashSet<>());
    set.add(curr[1]);
    map.put(curr[0], set);
  }

  private void euler(int node, HashMap<Integer, Set<Integer>> map, HashSet<Integer> visited) {
    ans++;
    if (!map.containsKey(node)) {
      return;
    }
    if (map.get(node).isEmpty()) {
      return;
    }

    visited.add(node);
    for (int child : map.getOrDefault(node, new HashSet<>())) {
      if (!visited.contains(child)) {
        euler(child, map, visited);
        ans++;
      }
    }
  }

  private boolean dfs(int curr, HashMap<Integer, Set<Integer>> map, HashMap<Integer, Set<Integer>> cloneMap, HashSet<Integer> visited,
      List<Boolean> hasApple) {
    if (!map.containsKey(curr)) {
      return hasApple.get(curr);
    }
    if (map.get(curr).isEmpty()) {
      return hasApple.get(curr);
    }
    visited.add(curr);
    boolean ret = false;
    for (int child : map.get(curr)) {
      if (!visited.contains(child)) {
        boolean currRet = dfs(child, map, cloneMap, visited, hasApple);
        if (!currRet && !hasApple.get(child)) {
          cloneMap.get(curr).remove(child);
        }

        ret = ret || currRet || hasApple.get(child);
      }
    }
    return ret;
  }

  public static void main(String args[]) {
    MinTimeApples m = new MinTimeApples();
    int[][] arr = new int[][]{
        {0, 1}, {1, 2}, {0, 3}, {3, 4}, {2, 5}, {4, 6}
    };
    List<Boolean> hasApple = Lists.newArrayList(true, false, true, true, true, false, false);
    System.out.println(m.minTime(7, arr, hasApple));
  }

}
