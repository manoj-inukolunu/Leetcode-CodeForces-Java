package com.binarysearch;

import java.util.TreeMap;

public class StockOrder {

  public int solve(int[][] orders) {
    TreeMap<Integer, Integer> buy = new TreeMap();
    TreeMap<Integer, Integer> sell = new TreeMap();
    for (int[] order : orders) {
      if (order[2] == 0) {
        buy.put(order[0], buy.getOrDefault(order[0], 0) + order[1]);
      } else {
        sell.put(order[0], sell.getOrDefault(order[0], 0) + order[1]);
      }
    }
    int count = 0;
    for (int[] order : orders) {
      if (order[2] == 0) {
        Integer lower = sell.lowerKey(order[0]);
        if (lower != null) {
          int sold = buy.get(order[0]) <= sell.get(lower) ? buy.get(order[0]) : sell.get(lower);
          count += sold;
          sell.put(lower, sell.get(lower) - sold);
          if (sell.get(lower) <= 0) {
            sell.remove(lower);
          }
          buy.put(order[0], buy.get(order[0]) - sold);
          if (buy.get(order[0]) <= 0) {
            buy.remove(order[0]);
          }
        }
      } else {
        Integer higher = buy.higherKey(order[0]);
        if (higher != null) {
          int bought = sell.get(order[0]) <= buy.get(higher) ? sell.get(order[0]) : buy.get(higher); //Math.abs(sell.get(order[0]) - buy.get(higher));
          count += (bought);
          buy.put(higher, buy.get(higher) - bought);
          if (buy.get(higher) <= 0) {
            buy.remove(higher);
          }
          sell.put(order[0], sell.get(order[0]) - bought);
          if (sell.get(order[0]) <= 0) {
            sell.remove(order[0]);
          }
        }
      }
    }
    return count;
  }

  public static void main(String args[]) {
    StockOrder s = new StockOrder();
    int[][] arr = new int[][]{
        {150, 5, 0}, {190, 1, 1}, {200, 1, 1}, {100, 9, 0}, {140, 8, 1}, {210, 4, 0}
    };
    System.out.println(s.solve(arr));
  }

}
