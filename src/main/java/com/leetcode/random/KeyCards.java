package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class KeyCards {

  public List<String> alertNames(String[] keyName, String[] keyTime) {
    List<String> ans = new ArrayList<>();
    HashMap<String, TreeSet<Integer>> map = new HashMap<>();
    for (int i = 0; i < keyName.length; i++) {
      String name = keyName[i];
      String split[] = keyTime[i].split(":");
      int time = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
      TreeSet<Integer> set = map.getOrDefault(name, new TreeSet<>());
      set.add(time);
      map.put(name, set);
    }
    for (String user : map.keySet()) {
      if (hasCount(user, map)) {
        ans.add(user);
      }
    }

    Collections.sort(ans);
    return ans;
  }

  private boolean hasCount(String user, HashMap<String, TreeSet<Integer>> map) {
    TreeSet<Integer> counts = map.get(user);
    Integer curr = counts.first();
    while (curr != null) {
      int end = curr + 60;
      Integer next = counts.higher(curr);
      if (next != null && next <= end) {
        Integer last = counts.higher(next);
        if (last != null && last <= end) {
          return true;
        } else if (last == null) {
          return false;
        }
      } else if (next == null) {
        return false;
      }
      curr = next;
    }
    return false;
  }


}
