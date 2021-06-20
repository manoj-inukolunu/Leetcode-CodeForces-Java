package com.leetcode.random6;

import java.util.ArrayList;
import java.util.List;

public class MinFibNum {

  public int findMinFibonacciNumbers(int k) {
    List<Integer> fibs = new ArrayList<>();
    fibs.add(1);
    fibs.add(1);
    while (true) {
      int next = fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2);
      if (next > k) {
        break;
      }
      fibs.add(next);
    }
    int count = 0;
    while (k > 0) {
      int max = fibs.get(fibs.size() - 1);
      count += (k / max);
      k = k % max;
      fibs.remove(fibs.size() - 1);
    }
    return count;
  }

  public static void main(String args[]) {
    MinFibNum m = new MinFibNum();
    System.out.println(m.findMinFibonacciNumbers(19));
  }

}
