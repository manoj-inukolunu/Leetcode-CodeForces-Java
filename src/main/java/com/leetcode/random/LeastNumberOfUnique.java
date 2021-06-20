package com.leetcode.random;

import com.google.common.collect.Comparators;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class LeastNumberOfUnique {

  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    HashMap<Integer, Integer> map = new HashMap();
    for (int num : arr) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<Entry<Integer, Integer>> p = new PriorityQueue(new Comparator<Entry<Integer, Integer>>() {
      @Override
      public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
        return Integer.compare(o1.getValue(), o2.getValue());
      }
    });
    p.addAll(map.entrySet());
    while (k > 0) {
      Entry<Integer, Integer> curr = p.peek();
      if (k - curr.getValue() >= 0) {
        k -= curr.getValue();
        p.poll();
      } else {
        k -= curr.getValue();
      }
    }
    return p.size();
  }

  public static void main(String args[]) {
    LeastNumberOfUnique l = new LeastNumberOfUnique();
    System.out.println(l.findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3));
  }

}
