package com.leetcode.s60day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class KPairSmallestSum {

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<Integer[]> p = new PriorityQueue<>(Comparator.comparingInt(o -> o[0] + o[1]));
    for (int i = 0; i < nums2.length && i < k; i++) {
      p.add(new Integer[]{nums1[0], nums2[i], 0});
    }
    List<List<Integer>> ans = new ArrayList<>();
    while (k-- > 0 && !p.isEmpty()) {
      Integer[] curr = p.poll();
      ans.add(Arrays.asList(curr));
      if (curr[1] + 1 < nums2.length) {
        p.add(new Integer[]{curr[0], nums2[curr[1] + 1], curr[1] + 1});
      }
    }
    return ans;
  }
}
