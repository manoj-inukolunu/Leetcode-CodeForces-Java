package com.leetcode.random7;

public class WaterAndJug {

  public int pour(int fromCap, int toCap, int d) {
    int from = fromCap;
    int to = 0;
    int step = 1;
    while (from != d && to != d) {
      System.out.println(from + " " + to);
      int temp = Math.min(from, toCap - to);
      to += temp;
      from -= temp;
      step++;
      if (from == d || to == d) {
        break;
      }
      if (from == 0) {
        from = fromCap;
        step++;
      }
      if (to == toCap) {
        to = 0;
        step++;
      }
    }
    return step;
  }

  public static void main(String args[]) {
    WaterAndJug w = new WaterAndJug();
    System.out.println(w.pour(2, 6, 5));
  }

}
