package com.leetcode.random3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MaxProf {

  class Pair implements Comparable<Pair> {

    int diff, prof;

    public Pair(int diff, int prof) {
      this.diff = diff;
      this.prof = prof;
    }

    @Override
    public String toString() {
      return "Pair{" +
          "diff=" + diff +
          ", prof=" + prof +
          '}';
    }

    @Override
    public int compareTo(Pair o) {
      return Integer.compare(o.diff, diff);
    }
  }

  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] workers) {
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < difficulty.length; i++) {
      list.add(new Pair(difficulty[i], profit[i]));
    }
    Collections.sort(list, Comparator.comparingInt(o -> o.diff));
    int[] maxArr = new int[list.size()];
    maxArr[0] = list.get(0).prof;
    for (int i = 1; i < maxArr.length; i++) {
      maxArr[i] = Math.max(maxArr[i - 1], list.get(i).prof);
    }
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < list.size(); i++) {
      map.put(list.get(i).diff, maxArr[i]);
    }
    int count = 0;
    for (int i = 0; i < workers.length; i++) {
      int diff = workers[i];
      if (map.containsKey(diff)) {
        count += map.get(diff);
      } else {
        Integer lower = map.lowerKey(diff);
        if (lower != null) {
          count += map.get(lower);
        }
      }
    }
    return count;
  }

}
