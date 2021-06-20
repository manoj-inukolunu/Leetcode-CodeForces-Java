package com.leetcode.random6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class AvoidFlood {

  public int[] avoidFlood(int[] rains) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] ans = new int[rains.length];
    Arrays.fill(ans, -1);
    TreeSet<Integer> dry = new TreeSet<>();
    for (int i = 0; i < rains.length; i++) {
      if (rains[i] == 0) {
        dry.add(i);
      } else if (!map.containsKey(rains[i])) {
        map.put(rains[i], i);
        ans[i] = -1;
      } else if (map.containsKey(rains[i])) {
        int lastRain = map.get(rains[i]);
        Integer higher = dry.higher(lastRain);
        if (higher == null) {
          return new int[]{};
        } else {
          ans[higher] = rains[i];
          dry.remove(higher);
        }
        map.put(rains[i], i);
      }
    }
    for (int i = 0; i < ans.length; i++) {
      if (rains[i] == 0 && ans[i] < 0) {
        ans[i] = 1;
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    int[] rains = new int[]{1, 0, 2, 0, 3, 0, 2, 0, 0, 0, 1, 2, 3};
    AvoidFlood a = new AvoidFlood();
    System.out.println(Arrays.toString(a.avoidFlood(rains)));
  }

}
