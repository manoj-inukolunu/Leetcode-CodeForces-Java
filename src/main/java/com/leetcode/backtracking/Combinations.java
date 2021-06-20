package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/8/20.
 */
public class Combinations {

  Set<Set<Integer>> ans = new HashSet<>();

  public List<List<Integer>> combine(int n, int k) {
    recur(n, k, 0, 1, new HashSet<>());
    List<List<Integer>> list = new ArrayList();
    for (Set<Integer> set : ans) {
      list.add(new ArrayList<>(set));
    }
    return list;
  }

  public void recur(int n, int k, int curr, int idx, Set<Integer> set) {
    if (idx > n || curr > k) {
      if (set.size() == k) {
        ans.add(new HashSet<>(set));
      }
      return;
    }
    if (curr == k) {
      ans.add(new HashSet<>(set));
      return;
    }
    set.add(idx);
    recur(n, k, curr + 1, idx + 1, set);
    set.remove(idx);
    recur(n, k, curr, idx + 1, set);
  }

  public static void main(String args[]) {
    Combinations combinations = new Combinations();
    System.out.println(combinations.combine(4, 2));
  }

}
