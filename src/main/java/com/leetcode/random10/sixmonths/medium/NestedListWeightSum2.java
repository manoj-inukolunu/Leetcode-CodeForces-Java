package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author manoji on 7/29/20.
 */
public class NestedListWeightSum2 {

  private int max = Integer.MIN_VALUE;

  public int depthSumInverse(List<NestedInteger> nestedList) {
    TreeMap<Integer, List<Integer>> map = new TreeMap<>(Collections.reverseOrder());
    int sum = 0, depth = 1;
    for (int i = 0; i < nestedList.size(); i++) {
      dfs(nestedList.get(i), map, max);
    }

    for (int key : map.keySet()) {
      List<Integer> list = map.get(key);
      int curr = 0;
      for (int i = 0; i < list.size(); i++) {
        curr += (list.get(i));
      }
      sum += curr;
    }
    return sum;
  }

  private void dfs1(NestedInteger nestedInteger, TreeMap<Integer, List<Integer>> map, int depth) {
    if (nestedInteger.isInteger()) {
      List<Integer> list = map.getOrDefault(depth, new ArrayList<>());
      list.add(nestedInteger.getInteger());
      map.put(depth, list);
      return;
    }
    List<NestedInteger> list = nestedInteger.getList();
    for (int i = 0; i < list.size(); i++) {
      dfs(list.get(i), map, depth - 1);
    }
  }


  private void dfs(NestedInteger nestedInteger, TreeMap<Integer, List<Integer>> map, int depth) {
    if (nestedInteger.isInteger()) {
      max = Math.max(depth, max);
      return;
    }
    List<NestedInteger> list = nestedInteger.getList();
    for (int i = 0; i < list.size(); i++) {
      dfs(list.get(i), map, depth + 1);
    }
  }


}
