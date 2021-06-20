/*
package com.leetcode.facebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SplitArrayEqualSum {

  class Pair {

    int start;
    int end;

    public Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return start == pair.start &&
          end == pair.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }
  }


  public boolean splitArray(int[] nums) {
    int[] pre = new int[nums.length];
    pre[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      pre[i] = pre[i - 1] + nums[i];
    }

    HashMap<Integer, List<Pair>> map = new HashMap<>();
    HashMap<Integer, Set<Pair>> map1 = new HashMap<>();

    for (int i = 0; i < pre.length; i++) {
      for (int j = i; j < pre.length; j++) {
        int sum = getSum(pre, i, j);
        updateList(map, i, j, sum);
        updateSet(map1, i, j, sum);
      }
    }
    int len = nums.length;
    for (int i = 1; i < len; i++) {
      int s1 = getSum(pre, 0, i - 1);
      if (map.containsKey(s1)) {
        List<Pair> pairs = map.get(s1);
        List<Pair> next = new ArrayList<>();
        for (int j = 0; j < pairs.size(); j++) {
          if (pairs.get(j).start == i + 1) {
            next.add(pairs.get(j));
          }
        }
        Set<Pair> nextSet = map1.get(s1);
        List<Pair> next1 = new ArrayList<>();
        for (int j = 0; j < next.size(); j++) {
          if(next.get(j).end+2)
        }
      }
    }
    return false;
  }


  private void updateSet(HashMap<Integer, Set<Pair>> map, int i, int j, int sum) {
    if (map.containsKey(sum)) {
      Set<Pair> set = map.get(sum);
      set.add(new Pair(i, j));
    } else {
      Set<Pair> set = new HashSet<>();
      set.add(new Pair(i, j));
      map.put(sum, set);
    }
  }

  private void updateList(HashMap<Integer, List<Pair>> map, int i, int j, int sum) {
    if (map.containsKey(sum)) {
      List<Pair> set = map.get(sum);
      set.add(new Pair(i, j));
    } else {
      List<Pair> set = new ArrayList<>();
      set.add(new Pair(i, j));
      map.put(sum, set);
    }
  }

  int getSum(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] - pre[start - 1];
    }
    return pre[end];
  }

  public static void main(String args[]) {
    int[] arr = new int[]{1, 2, 1, 3, 0, 0, 2, 2, 1, 3, 3};
    SplitArrayEqualSum s = new SplitArrayEqualSum();

    System.out.println(s.splitArray(arr));
  }

}
*/
