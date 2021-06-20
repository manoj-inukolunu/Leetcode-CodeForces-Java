package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupPeople {

  class Pair {

    int size;
    List<Integer> group;

    public Pair(int size, List<Integer> group) {
      this.size = size;
      this.group = group;
    }
  }

  public List<List<Integer>> groupThePeople(int[] groupSizes) {
    HashMap<Integer, List<Pair>> map = new HashMap<>();

    for (int i = 0; i < groupSizes.length; i++) {
      int curr = groupSizes[i];
      List<Pair> groups = map.getOrDefault(curr, new ArrayList<>());
      boolean added = false;
      for (Pair p : groups) {
        if (p.group.size() < curr) {
          added = true;
          p.group.add(i);
          break;
        }
      }
      if (!added) {
        Pair pair = new Pair(curr, new ArrayList<>());
        pair.group.add(i);
        groups.add(pair);
        map.put(curr, groups);
      }
    }

    List<List<Integer>> ans = new ArrayList<>();
    for (int key : map.keySet()) {
      for (Pair p : map.get(key)) {
        ans.add(p.group);
      }
    }
    return ans;
  }


}
