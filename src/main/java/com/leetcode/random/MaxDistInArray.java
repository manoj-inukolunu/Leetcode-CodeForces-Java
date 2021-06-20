package com.leetcode.random;

import com.google.common.collect.Lists;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class MaxDistInArray {

  public int maxDistance(List<List<Integer>> arrays) {
    TreeMap<Integer, Set<Integer>> minMap = new TreeMap<>();
    TreeMap<Integer, Set<Integer>> maxMap = new TreeMap<>();
    for (int i = 0; i < arrays.size(); i++) {
      List<Integer> integers = arrays.get(i);
      Set<Integer> set = minMap.getOrDefault(integers.get(0), new HashSet<>());
      set.add(i);
      minMap.put(integers.get(0), set);
      set = maxMap.getOrDefault(integers.get(integers.size() - 1), new HashSet<>());
      set.add(i);
      maxMap.put(integers.get(integers.size() - 1), set);
    }
    int ans = Integer.MIN_VALUE;
    Integer min = minMap.firstKey();
    Integer max = maxMap.lastKey();
    Set<Integer> first = minMap.get(min);
    Set<Integer> last = maxMap.get(max);
    if (first.size() != last.size() || check(first, last)) {
      return Math.max(Math.abs(max - min), Math.abs(min - max));
    } else {
      // move max
      int tempMax = max;
      while (true) {
        max = maxMap.lowerKey(max);
        if (max == null) {
          break;
        }
        last = maxMap.get(max);
        if (check(first, last)) {
          ans = Math.max(Math.max(Math.abs(max - min), Math.abs(min - max)), ans);
        }
      }
      // move min
      max = tempMax;
      last = maxMap.get(max);
      while (true) {
        min = minMap.higherKey(min);
        if (min == null) {
          break;
        }
        first = minMap.get(min);
        if (check(first, last)) {
          ans = Math.max(Math.max(Math.abs(max - min), Math.abs(min - max)), ans);
        }
      }
    }
    return ans;
  }

  private boolean check(Set<Integer> minSet, Set<Integer> maxSet) {
    if (minSet.size() == 1 && maxSet.size() == 1) {
      for (int a : minSet) {
        if (maxSet.contains(a)) {
          return false;
        }
      }
      return true;
    }
    return maxSet.size() != minSet.size() || (minSet.size() > 1 && maxSet.size() > 1);
  }

  public static void main(String args[]) {
    MaxDistInArray m = new MaxDistInArray();
    //[[-2],[-3,-2,1]]
    List<List<Integer>> list = Lists.newArrayList(Lists.newArrayList(-2), Lists.newArrayList(-3, -2, 1));
    System.out.println(m.maxDistance(list));
  }
}
