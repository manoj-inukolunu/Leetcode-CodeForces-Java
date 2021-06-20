package com.leetcode.random1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SummaryRanges {

  /**
   * Initialize your data structure here.
   */
  List<Integer> list = new ArrayList();
  HashSet<Integer> set = new HashSet();

  public SummaryRanges() {

  }

  public void addNum(int val) {
    int pos = Collections.binarySearch(list, val);
    if (pos < 0) {
      list.add(-(1 + pos), val);
    }
    set.add(val);
  }

  public int[][] getIntervals() {
    List<int[]> ans = new ArrayList();
    for (int i = 0; i < list.size(); i++) {
      int start = list.get(i), end = list.get(i);
      while (set.contains(end)) {
        end++;
        i++;
      }
      i--;
      ans.add(new int[]{start, end - 1});
    }
    int[][] ret = new int[ans.size()][2];
    for (int i = 0; i < ans.size(); i++) {
      ret[i] = ans.get(i);
    }
    return ret;
  }

  public static void main(String args[]) {
    SummaryRanges s = new SummaryRanges();
    s.addNum(1);
    System.out.println(Arrays.deepToString(s.getIntervals()));
    s.addNum(3);
    System.out.println(Arrays.deepToString(s.getIntervals()));
    s.addNum(7);
    System.out.println(Arrays.deepToString(s.getIntervals()));
    s.addNum(2);
    System.out.println(Arrays.deepToString(s.getIntervals()));
    s.addNum(6);
    System.out.println(Arrays.deepToString(s.getIntervals()));
  }
}
