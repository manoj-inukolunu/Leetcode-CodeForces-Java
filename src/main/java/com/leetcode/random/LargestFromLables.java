package com.leetcode.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class LargestFromLables {

  public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {

    HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();

    for (int i = 0; i < values.length; i++) {
      int val = values[i];
      int label = labels[i];
      PriorityQueue<Integer> p = map.getOrDefault(label, new PriorityQueue<>(Collections.reverseOrder()));
      p.add(val);
      map.put(label, p);
    }

    List<Integer> list = new ArrayList<>();

    for (int key : map.keySet()) {
      PriorityQueue<Integer> p = map.get(key);
      int count = use_limit;
      while (count > 0) {
        list.add(p.poll());
        count--;
      }
    }

    Collections.sort(list, Collections.reverseOrder());

    int max = 0;
    for (int i = 0; i < num_wanted; i++) {
      max += list.get(i);
    }
    return max;
  }

}
