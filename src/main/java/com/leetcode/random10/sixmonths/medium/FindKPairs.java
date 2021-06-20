package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author manoji on 8/5/20.
 */
public class FindKPairs {

  class Triplet {

    int num1;
    int num2;
    int sum;

    public Triplet(int num1, int num2, int sum) {
      this.num1 = num1;
      this.num2 = num2;
      this.sum = sum;
    }
  }

  public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<Triplet> p = new PriorityQueue<>(Comparator.comparingInt(value -> value.sum));
    for (int i = 0; i < nums1.length; i++) {
      for (int j = 0; j < nums2.length; j++) {
        p.add(new Triplet(nums1[i], nums2[j], nums1[i] + nums2[j]));
      }
    }
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      Triplet triplet = p.poll();
      List<Integer> l = new ArrayList<>();
      l.add(triplet.num1, triplet.num2);
      list.add(l);
    }
    return list;
  }
}
