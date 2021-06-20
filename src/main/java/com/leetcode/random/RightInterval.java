package com.leetcode.random;

import java.util.Arrays;
import java.util.TreeSet;

public class RightInterval {


  class Pair implements Comparable<Pair> {

    int start;
    int end;
    int index;

    public Pair(int start, int end, int index) {
      this.start = start;
      this.end = end;
      this.index = index;
    }

    @Override
    public int compareTo(Pair o) {
      if (o.start == this.start && o.end == this.end) {
        return 0;
      } else if (o.start >= this.end) {
        return -1;
      } else {
        return 1;
      }
    }
  }

  public int[] findRightInterval(int[][] intervals) {
    TreeSet<Pair> set = new TreeSet<>();
    for (int i = 0; i < intervals.length; i++) {
      set.add(new Pair(intervals[i][0], intervals[i][1], i));
    }
    int[] ans = new int[intervals.length];
    Arrays.fill(ans, -1);
    for (int i = 0; i < intervals.length; i++) {
      Pair next = set.higher(new Pair(intervals[i][0], intervals[i][1], i));
      ans[i] = next == null ? -1 : next.index;
    }
    return ans;
  }
}
