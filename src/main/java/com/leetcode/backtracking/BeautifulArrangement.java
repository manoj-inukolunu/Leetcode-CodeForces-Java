package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author manoji on 7/9/20.
 */
public class BeautifulArrangement {

  private Set<List<Integer>> set = new HashSet();

  public int countArrangement(int N) {
    recur(N, 1, new ArrayList<>());
    return set.size();
  }

  private void recur(int total, int pos, List<Integer> collector) {
    if (collector.size() >= total) {
      set.add(new ArrayList<>(collector));
      return;
    }
    if (pos > total) {
      set.add(new ArrayList<>(collector));
      return;
    }
    for (int curr = 1; curr <= total; curr++) {
      if ((curr % pos == 0 || pos % curr == 0) && !collector.contains(curr)) {
        collector.add(curr);
        recur(total, pos + 1, collector);
        collector.remove(new Integer(curr));
      }
    }
  }


  public static void main(String args[]) {
    BeautifulArrangement b = new BeautifulArrangement();

    System.out.println(b.countArrangement(1));
  }
}
