package com.leetcode.random6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MeetingScheduler {


  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    for (int i = 0; i < slots1.length; i++) {
      map.put(slots1[i][0], Math.max(map.getOrDefault(slots1[i][0], 0), slots1[i][1]));
    }
    List<Integer> ans = new ArrayList<>();

    Arrays.sort(slots2, Comparator.comparingInt(value -> value[0]));

    for (int i = 0; i < slots2.length; i++) {
      int[] curr = slots2[i];
      if (map.containsKey(curr[0])) {
        int end = map.get(curr[0]);
        if (curr[0] + duration <= end && curr[0] + duration <= curr[1]) {
          ans.add(curr[0]);
          ans.add(curr[0] + duration);
          return ans;
        }
      } else {
        Integer lower = map.lowerKey(curr[0]);
        if (lower != null) {
          Integer end = map.get(lower);
          if (curr[0] + duration <= end && curr[0] + duration <= curr[1]) {
            ans.add(curr[0]);
            ans.add(curr[0] + duration);
            return ans;
          }
        }
        Integer higher = map.higherKey(curr[0]);
        if (higher != null) {
          int end = higher + duration;
          int start = higher;
          if (end <= curr[1] && map.get(higher) >= end) {
            ans.add(start);
            ans.add(start + duration);
            return ans;
          }
        }
      }
    }

    return ans;
  }

  public static void main(String args[]) {
    int[][] slots1 = new int[][]{
        {0, 1}, {100, 1000100}
    };
    int[][] slots2 = new int[][]{
        {90, 1000100}, {0, 2}
    };
    MeetingScheduler m = new MeetingScheduler();
    System.out.println(m.minAvailableDuration(slots1, slots2, 1000000));

  }

}
