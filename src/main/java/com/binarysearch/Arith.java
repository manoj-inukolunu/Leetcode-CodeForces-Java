package com.binarysearch;

import java.util.TreeMap;

public class Arith {

  public int solve(int[] nums, int[][] queries) {
    TreeMap<Integer, Integer> map = new TreeMap();
    int i = 0, start = -1;
    while (i < nums.length) {
      if (i + 1 < nums.length && i - 1 >= 0 && nums[i + 1] - nums[i] == nums[i] - nums[i - 1]) {
        start = i - 1;
        int diff = nums[i + 1] - nums[i];
        while (i + 1 < nums.length && nums[i + 1] - nums[i] == diff) {
          i++;
        }
        map.put(start, i);
      } else {
        i++;
      }
    }
    int count = 0;
    System.out.println(map);
    for (int j = 0; j < queries.length; j++) {
      int[] curr = queries[j];
      if (curr[1] - curr[0] <= 1) {
        count++;
      } else if (map.containsKey(curr[0]) && map.get(curr[0]) >= curr[1]) {
        count++;
      } else {
        Integer lower = map.lowerKey(curr[0]);
        if (lower != null && lower <= curr[0] && map.get(lower) >= curr[1]) {
          count++;
        } else {
          Integer higher = map.higherKey(curr[0]);
          if (higher != null && higher <= curr[0] && map.get(higher) >= curr[1]) {
            count++;
          }
        }

      }
    }
    return count;
  }

  public static void main(String args[]) {
    Arith a = new Arith();
    int[] arr = new int[]{3, 1, 5, 7, 9, 12, 15};
    int[][] qu = new int[][]{
        {0, 1}, {0, 2}, {4, 6}, {3, 5}
    };
    System.out.println(a.solve(arr, qu));
  }

}
