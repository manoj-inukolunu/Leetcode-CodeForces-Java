package com.leetcode.random10.sixmonths.medium;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class ReconstructItinerary {


  List<String> ans = new ArrayList();

  List<String> finalAns = new ArrayList<>();


  class Edge {

    String from;
    String to;

    public Edge(String from, String to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Edge edge = (Edge) o;
      return Objects.equals(from, edge.from) &&
          Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
      return Objects.hash(from, to);
    }
  }

  public List<String> findItinerary(List<List<String>> tickets) {
    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    for (List<String> str : tickets) {
      String src = str.get(0);
      String dst = str.get(1);
      PriorityQueue<String> set = map.getOrDefault(src, new PriorityQueue<>());
      set.add(dst);
      map.put(src, set);
    }
    String src = "JFK";
    ans.add(src);
    dfs(src, map, 0, tickets.size());
    return finalAns;

  }

  private boolean dfs(String curr, HashMap<String, PriorityQueue<String>> map, int count, int total) {
    if (map.get(curr) != null) {
      PriorityQueue<String> pr = map.get(curr);
      while (!pr.isEmpty()) {
        String dst = pr.poll();
        ans.add(dst);
        boolean found = dfs(dst, map, count + 1, total);
        if (!found) {
          ans.remove(dst);
        } else {
          finalAns = new ArrayList<>(ans);
          return true;
        }
      }
    }
    return count == total;
  }


  public static void main(String args[]) {
    ReconstructItinerary r = new ReconstructItinerary();
    System.out.println(r.findItinerary(Lists.newArrayList(Lists.newArrayList("JFK", "KUL"), Lists.newArrayList("JFK", "NRT"),
        Lists.newArrayList("NRT", "JFK"))));
  }

}
