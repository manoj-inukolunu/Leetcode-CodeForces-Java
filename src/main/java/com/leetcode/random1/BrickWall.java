package com.leetcode.random1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrickWall {


  public int leastBricks(List<List<Integer>> wall) {
    List<List<Integer>> list = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < wall.size(); i++) {
      List<Integer> curr = wall.get(i);
      List<Integer> pre = new ArrayList<>();
      pre.add(curr.get(0));
      for (int j = 1; j < curr.size(); j++) {
        pre.add(pre.get(j - 1) + curr.get(j));
      }
      sum = pre.get(pre.size() - 1);
      list.add(pre);
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    int full = 0;
    int max = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).size() == 1) {
        full++;
      }
      for (int j = 0; j < list.get(i).size(); j++) {
        int curr = list.get(i).get(j);
        map.put(curr, map.getOrDefault(curr, 0) + 1);
      }
    }

    for (int key : map.keySet()) {
      if (key != sum) {
        max = Math.max(max, map.get(key));
      }
    }

    if (max == wall.size()) {
      return max;
    }

    return wall.size() - max;

  }

}
