package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class ReconstructItineraryHierHolzer {

  public List<String> findItinerary(List<List<String>> tickets) {
    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    for (List<String> list : tickets) {
      String src = list.get(0);
      String dst = list.get(1);
      PriorityQueue<String> priorityQueue = map.getOrDefault(src, new PriorityQueue<>());
      priorityQueue.add(dst);
      map.put(src, priorityQueue);
    }

    List<String> ans = new ArrayList<>();
    dfs("JFK", map, ans);
    Collections.reverse(ans);
    return ans;
  }

  private void dfs(String src, HashMap<String, PriorityQueue<String>> map, List<String> ans) {
    PriorityQueue<String> pr = map.get(src);
    if (pr != null) {
      while (!pr.isEmpty()) {
        String next = pr.poll();
        dfs(next, map, ans);
      }
    }
    ans.add(src);
  }


}
