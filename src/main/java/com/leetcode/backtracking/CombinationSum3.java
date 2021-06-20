package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/8/20.
 */
public class CombinationSum3 {

  public Set<Set<Integer>> set = new HashSet<>();

  public List<List<Integer>> combinationSum3(int k, int n) {
    recur(k, n, 0, 0, 1, new HashSet<>());

    List<List<Integer>> ans = new ArrayList<>();
    for (Set<Integer> it : set) {
      ans.add(new ArrayList<>(it));
    }
    return ans;
  }


  public void recur(int k, int n, int sum, int currK, int currIdx, Set<Integer> collector) {
    if (currIdx > 9) {
      if (sum == n && currK == k) {
        set.add(new HashSet<>(collector));
      }
      return;
    }
    if (currK >= k) {
      if (sum == n && currK == k) {
        set.add(new HashSet<>(collector));

      }
      return;
    }
    if (sum < n) {
      collector.add(currIdx);
      recur(k, n, sum + currIdx, currK + 1, currIdx + 1, collector);
      collector.remove(currIdx);
    }
    recur(k, n, sum, currK, currIdx + 1, collector);
  }

  public static void main(String args[]) {
    CombinationSum3 c = new CombinationSum3();
    System.out.println(c.combinationSum3(3, 9));
  }

}
