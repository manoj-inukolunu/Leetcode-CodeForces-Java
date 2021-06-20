package com.leetcode.random10.sixmonths.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class MinimumAreaRectangle {

  public int minAreaRect(int[][] points) {
    int area = Integer.MAX_VALUE;
    TreeMap<Integer, TreeSet<Integer>> xMap = new TreeMap<>();
    for (int[] row : points) {
      int x = row[0];
      int y = row[1];
      TreeSet<Integer> xSet = xMap.getOrDefault(x, new TreeSet<>());
      xSet.add(y);
      xMap.put(x, xSet);
    }

    List<Integer> xPoints = new ArrayList<>(xMap.keySet());
    for (int i = 0; i < xPoints.size(); i++) {
      int x1 = xPoints.get(i);
      List<Integer> yPoints = new ArrayList<>(xMap.get(x1));
      for (int j = 0; j < yPoints.size(); j++) {
        for (int k = j + 1; k < yPoints.size(); k++) {
          int y1 = yPoints.get(j);
          int y2 = yPoints.get(k);
          for (int l = i + 1; l < xPoints.size(); l++) {
            int x2 = xPoints.get(l);
            TreeSet<Integer> ySet = xMap.get(x2);
            if (ySet.contains(y1) && ySet.contains(y2)) {
              area = Math.min((y2 - y1) * (x2 - x1), area);
            }
          }
        }
      }
    }
    return area;
  }

  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}
    };
    MinimumAreaRectangle m = new MinimumAreaRectangle();
    System.out.println(m.minAreaRect(arr));
  }

}
