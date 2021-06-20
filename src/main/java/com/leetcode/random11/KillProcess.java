package com.leetcode.random11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KillProcess {

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < pid.size(); i++) {
      List<Integer> child = map.getOrDefault(ppid.get(i), new ArrayList<>());
      child.add(pid.get(i));
      map.put(ppid.get(i), child);
    }
    Set<Integer> ans = new HashSet<>();
    HashSet<Integer> visited = new HashSet<>();
    dfs(kill, map, ans, visited);
    return new ArrayList<>(ans);
  }

  private void dfs(Integer procId, HashMap<Integer, List<Integer>> map, Set<Integer> collector, HashSet<Integer> visited) {
    if (!visited.contains(procId)) {
      visited.add(procId);
      collector.add(procId);
      if (map.containsKey(procId)) {
        for (int child : map.get(procId)) {
          if (!visited.contains(child)) {
            dfs(child, map, collector, visited);
          }
        }
      }
    }
  }

}
