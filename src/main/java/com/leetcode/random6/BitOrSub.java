package com.leetcode.random6;

import java.util.HashSet;
import java.util.Set;

public class BitOrSub {


  public int subarrayBitwiseORs(int[] a) {
    Set<Integer> ans = new HashSet<>(), prev = new HashSet<>();
    for (int num : a) {
      HashSet<Integer> next = new HashSet<>();
      for (int i : prev) {
        next.add(num | i);
      }
      next.add(num);
      ans.addAll(next);
      prev = next;
    }
    return ans.size();
  }


  public static void main(String args[]) {
    BitOrSub b = new BitOrSub();
    System.out.println(b.subarrayBitwiseORs(new int[]{1, 1, 2}));
  }

}
