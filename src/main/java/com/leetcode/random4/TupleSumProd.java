package com.leetcode.random4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TupleSumProd {


  public int tupleSameProduct(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    int count = 0;
    HashMap<Long, List<int[]>> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (i != j) {
          long prod = (long) nums[i] * nums[j];
          List<int[]> s = map.getOrDefault(prod, new ArrayList<>());
          s.add(new int[]{nums[i], nums[j]});
          map.put(prod, s);
        }
      }
    }
    for (long key : map.keySet()) {
      if (map.get(key).size() > 0) {
        List<int[]> list = map.get(key);
        for (int i = 0; i < list.size(); i++) {
          int[] curr = list.get(i);
          top:
          for (int j = i + 1; j < list.size(); j++) {
            int[] next = list.get(j);
            if (curr[0] == next[0] || curr[0] == next[1] || curr[1] == next[0] || curr[1] == next[1]) {
              continue top;
            }
            count += 2;
          }
        }

      }
    }
    return count;
  }

  public static void main(String args[]) {
    TupleSumProd t = new TupleSumProd();
    System.out.println(t.tupleSameProduct(new int[]{2, 3, 4, 6, 8, 12}));
  }

}
