package com.leetcode.random3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EatenApples {

  public int eatenApples(int[] apples, int[] days) {
    PriorityQueue<Integer[]> p = new PriorityQueue<>(Comparator.comparing(integers -> integers[1]));
    int count = 0, day = 1;
    for (int i = 0; i < apples.length; i++, day++) {
      if (apples[i] != 0) {
        p.add(new Integer[]{apples[i], i + days[i]});
      }
      while (!p.isEmpty() && p.peek()[1] < day) {
        p.poll();
      }
      if (!p.isEmpty()) {
        Integer[] top = p.poll();
        if (top[0] >= 1) {
          count++;
          top[0]--;
          if (top[0] > 0) {
            p.add(top);
          }
        }
      }
    }
    while (!p.isEmpty()) {
      while (!p.isEmpty() && p.peek()[1] < day) {
        p.poll();
      }
      if (!p.isEmpty()) {
        Integer[] top = p.poll();
        if (top[0] >= 1) {
          count++;
          top[0]--;
          if (top[0] > 0) {
            p.add(top);
          }
        }
      }
      day++;
    }
    return count;
  }

  public static void main(String args[]) {
    EatenApples e = new EatenApples();
    int[] apples = new int[]{1, 2, 3, 5, 2};
    int[] days = new int[]{3, 2, 1, 4, 2};
    System.out.println(e.eatenApples(apples, days));
  }

}
