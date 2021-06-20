package com.leetcode.random;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortByPowers {

  public int getKth(int lo, int hi, int k) {
    HashMap<Integer, Integer> graph = new HashMap<>();
    HashMap<Integer, Integer> pMap = new HashMap<>();
    HashSet<Integer> set = new HashSet<>();
    PriorityQueue<Integer[]> p = new PriorityQueue<>(new Comparator<Integer[]>() {
      @Override
      public int compare(Integer[] o1, Integer[] o2) {
        if (o1[0].equals(o2[0])) {
          return Integer.compare(o1[1], o2[1]);
        }
        return Integer.compare(o1[0], o2[0]);
      }
    });
    for (int i = lo; i <= hi; i++) {
      if (!graph.containsKey(i)) {
        bfs(i, graph);
      } else if (!pMap.containsKey(i)) {
        int val = dfs(i, graph, pMap);
        pMap.put(i, val);
        p.add(new Integer[]{val, i});
        set.add(i);
      }
    }
    for (int i = lo; i <= hi; i++) {
      if (!pMap.containsKey(i)) {
        int val = dfs(i, graph, pMap);
        pMap.put(i, val);
        if (!set.contains(i)) {
          p.add(new Integer[]{val, i});
        }
      } else {
        if (!set.contains(i)) {
          p.add(new Integer[]{pMap.get(i), i});
        }
      }
    }
    int ans = 0;
    while (k > 0) {
      ans = p.poll()[1];
      k--;
    }
    return ans;
  }

  private int dfs(int node, HashMap<Integer, Integer> graph, HashMap<Integer, Integer> pMap) {
    if (node == 1) {
      pMap.put(node, 0);
      return 0;
    }
    if (pMap.containsKey(node)) {
      return pMap.get(node);
    }
    int power = 1 + dfs(graph.get(node), graph, pMap);
    if (!graph.containsKey(node)) {
      pMap.put(graph.get(node), power);
    }
    return power;
  }

  private void bfs(int node, HashMap<Integer, Integer> graph) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(node);
    HashSet<Integer> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Integer curr = queue.poll();
      if (!visited.contains(curr) && !graph.containsKey(curr) && curr != 1) {
        visited.add(curr);
        if (curr % 2 == 0) {
          graph.put(curr, curr / 2);
          queue.add(curr / 2);
        } else {
          graph.put(curr, (3 * curr) + 1);
          queue.add(3 * curr + 1);
        }
      }
    }
  }

  public static void main(String args[]) {
    SortByPowers s = new SortByPowers();
    System.out.println(s.getKth(1, 1000, 777));
  }

}
