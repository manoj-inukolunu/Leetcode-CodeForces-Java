package com.leetcode.random4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxVacationdays {

  HashMap<String, Integer> dp = new HashMap<>();

  public int maxVacationDays(int[][] flights, int[][] days) {
    HashMap<Integer, List<Integer>> map = new HashMap();
    for (int i = 0; i < flights.length; i++) {
      for (int j = 0; j < flights.length; j++) {
        if (flights[i][j] == 1) {
          List<Integer> list = map.getOrDefault(i, new ArrayList());
          list.add(j);
          map.put(i, list);
        }
      }
    }
    System.out.println(map);
    return dfs(0, 0, map, days);
  }

  private int dfs(int city, int week, HashMap<Integer, List<Integer>> flights, int[][] days) {
    String key = city + ":" + week;
    if (dp.containsKey(key)) {
      return dp.get(key);
    }
    if (week >= days[0].length) {
      return 0;
    }
    int best = Integer.MIN_VALUE;
    //stay
    best = Math.max(best, days[city][week] + dfs(city, week + 1, flights, days));

    //go
    if (flights.containsKey(city)) {
      for (int i = 0; i < flights.get(city).size(); i++) {
        best = Math.max(best, days[flights.get(city).get(i)][week] + dfs(flights.get(city).get(i), week + 1, flights, days));
      }
    }
    dp.put(key, best);
    return best;
  }

  public static void main(String args[]) {
    int[][] flights = new int[][]{
        {0, 1, 1}, {1, 0, 1}, {1, 1, 0}
    };
    int[][] days = new int[][]{
        {1, 3, 1}, {6, 0, 3}, {3, 3, 3}
    };
    MaxVacationdays m = new MaxVacationdays();
    System.out.println(m.maxVacationDays(flights, days));
  }

}
