package com.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class ShortestDistanceToTargetColor {


  public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap();
    for (int i = 0; i < colors.length; i++) {
      TreeSet<Integer> set = map.getOrDefault(colors[i], new TreeSet());
      set.add(i);
      map.put(colors[i], set);
    }

    List<Integer> ans = new ArrayList();
    for (int i = 0; i < queries.length; i++) {
      TreeSet<Integer> querySet = map.get(queries[i][1]);
      if (querySet == null) {
        ans.add(-1);
      } else {
        if (queries[i][0] < colors.length && colors[queries[i][0]] == queries[i][1]) {
          ans.add(0);
        } else {
          Integer h = querySet.higher(queries[i][0]);
          Integer l = querySet.lower(queries[i][0]);
          if (h == null && l == null) {
            ans.add(-1);
          } else if (h == null && l != null) {
            ans.add(Math.abs(l - queries[i][0]));
          } else if (h != null && l == null) {
            ans.add(Math.abs(h - queries[i][0]));
          } else if (h != null && l != null) {
            ans.add(Math.min(Math.abs(queries[i][0] - h), Math.abs(queries[i][0] - l)));
          }
        }
      }
    }
    return ans;
  }

  public static void main(String args[]) {
    ShortestDistanceToTargetColor s = new ShortestDistanceToTargetColor();
    int[] color = new int[]{1, 2};
    int[][] queries = new int[][]{
        {0, 3}
    };

    System.out.println(s.shortestDistanceColor(color, queries));
  }


}
