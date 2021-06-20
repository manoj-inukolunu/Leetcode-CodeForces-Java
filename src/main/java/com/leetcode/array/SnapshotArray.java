package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 6/1/20.
 */
public class SnapshotArray {

  class Pair {

    int index;
    int val;

    public Pair(int index, int val) {
      this.index = index;
      this.val = val;
    }
  }

  HashMap<Integer, List<Pair>> map = new HashMap<>();
  int[] arr;
  int currentSnapId;
  Set<Integer> changed = new HashSet<>();

  public SnapshotArray(int length) {
    arr = new int[length];
  }

  public void set(int index, int val) {
    arr[index] = val;
    changed.add(index);

  }

  public int snap() {
    List<Pair> pairs = map.getOrDefault(currentSnapId, new ArrayList<>());
    for (Integer index : changed) {
      pairs.add(new Pair(index, arr[index]));
    }
    map.put(currentSnapId, pairs);
    return currentSnapId++;
  }

  public int get(int index, int snap_id) {
    List<Pair> pairs = map.get(snap_id);
    for (Pair pair : pairs) {
      if (pair.index == index) {
        return pair.val;
      }
    }
    return -1;
  }
}

