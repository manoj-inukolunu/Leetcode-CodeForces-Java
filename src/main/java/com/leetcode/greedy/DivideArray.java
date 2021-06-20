package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DivideArray {

  public boolean isPossibleDivide(int[] nums, int k) {
    if (nums.length % k != 0) {
      return false;
    }
    TreeMap<Integer, Integer> map = new TreeMap();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int count = 0, start = map.firstKey(), total = 0, len = 0;
    List<Integer> list = new ArrayList<>();
    while (total < nums.length) {
      do {
        list.add(start);
        total++;
        len++;
        if (map.get(start) - 1 == 0) {
          map.remove(start);
        } else {
          map.put(start, map.get(start) - 1);
        }
        start = start + 1;
      } while (map.containsKey(start) && len < k);
      if (list.size() == k) {
        count++;
      }
      if (map.isEmpty()) {
        break;
      }
      start = map.firstKey();
      list.clear();
      len=0;
    }
    return count == nums.length / k;
  }

  public static void main(String args[]) {
    DivideArray d = new DivideArray();
    System.out.println(d.isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
  }

}
