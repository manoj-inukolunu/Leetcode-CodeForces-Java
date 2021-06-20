package com.leetcode.contest;

import java.util.Arrays;
import java.util.HashMap;

public class WeightedJobScheduling {

  HashMap<String, Integer> dp = new HashMap<>();

  public int maxValue(int[][] events, int k) {
    Arrays.sort(events, (o1, o2) -> Integer.compare(o1[1], o2[1]));
    return dfs(events, 0, -1, k);
  }

  private int dfs(int[][] events, int idx, int endDay, int k) {
    if (idx >= events.length) {
      return 0;
    }
    if (k == 0) {
      return 0;
    }
    String key = idx + "|" + endDay + "|" + k;
    if (dp.containsKey(key)) {
      return dp.get(key);
    }
    int incl = Integer.MIN_VALUE;
    if (events[idx][0] > endDay && k > 0) {
      incl = events[idx][2] + dfs(events, idx + 1, events[idx][1], k - 1);
    }

    int excl = dfs(events, idx + 1, endDay, k);
    dp.put(key, Math.max(incl, excl));
    return Math.max(incl, excl);
  }

  /*
  [[87,95,42],[3,42,37],[20,42,100],[53,84,80],[10,88,38],[25,80,57],[18,38,33]]
3
   */

  public static void main(String[] args) {
    WeightedJobScheduling w = new WeightedJobScheduling();
    int[][] events = new int[][]{{87, 95, 42}, {3, 42, 37}, {20, 42, 100}, {53, 84, 80}, {10, 88, 38}, {25, 80, 57}, {18, 38, 33}};
    System.out.println(w.maxValue(events, 3));
  }


}
