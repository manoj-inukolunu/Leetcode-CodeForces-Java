package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LC2242 {

  class Pair {
    int first, second;

    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object other) {
      Pair o = (Pair) other;
      return this.first == o.first && this.second == o.second;
    }
  }

  public int maximumScore(int[] scores, int[][] edges) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[0];
      List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
      list.add(edge[1]);
      map.put(edge[0], list);
      list = map.getOrDefault(edge[1], new ArrayList<>());
      list.add(edge[0]);
      map.put(edge[1], list);
    }
    for (int key : map.keySet()) {
      Collections.sort(map.get(key), (o1, o2) -> -Integer.compare(scores[o1], scores[o2]));
      List<Integer> list = map.get(key);
      if (list.size() > 3) {
        map.put(key, list.subList(0, 3));
      }
    }
    System.out.println(map);
    int ans = 0;
    for (int i = 0; i < edges.length; i++) {
      int[] edge = edges[i];
      Set<Integer> set = new HashSet<>();
      set.add(edge[0]);
      set.add(edge[1]);
      int currScore = 0;
      for (int node : map.getOrDefault(edge[0], new ArrayList<>())) {
        if (set.contains(node)) {
          continue;
        }
        set.add(node);
        for (int n : map.getOrDefault(edge[1], new ArrayList<>())) {
          if (set.contains(n)) {
            continue;
          }
          set.add(n);
          if (set.size() == 4) {
            System.out.println("Here");
            for (int nd : set) {
              currScore += scores[nd];
            }
            ans = Math.max(ans, currScore);
          }
          set.remove(n);
        }
        set.remove(node);
      }
    }
    return ans;
  }
}
