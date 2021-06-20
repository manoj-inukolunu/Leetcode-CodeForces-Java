package com.leetcode.queue;

import java.util.Collections;
import java.util.PriorityQueue;

public class MaxProf {

  int mod = (int) Math.pow(10, 9) + 7;

  int sumAll(long first, long second) {
    return (int) (((Math.abs(first - second) + 1) % mod * (first + second) % mod / 2) % mod);
  }

  public int maxProfit(int[] inventory, int orders) {
    int ans = 0;
    int count = 0;
    PriorityQueue<Integer> p = new PriorityQueue(Collections.reverseOrder());
    for (int num : inventory) {
      p.add(num);
    }
    while (!p.isEmpty() && count < orders) {
      int curr = p.poll();
      int need = orders - count;
      if (!p.isEmpty()) {
        int next = p.poll();
        if (need == 1) {
          ans = ans % mod + curr % mod;
          count++;
        } else if (curr - need > next) {
          int sum = sumAll(curr - need, curr);
          ans = ans % mod + sum % mod;
          count += (curr - need);
        } else {
          int sum = sumAll(next, curr);
          ans = ans % mod + sum % mod;
          p.add(next - -1);
          p.add(next);
          count += (curr - next + 1);
        }
      } else {
        int sum = sumAll(curr - need + 1, curr) % mod;
        ans = ans % mod + sum % mod;
        count += (curr - need + 1);
      }
    }
    return ans % mod;
  }

  public static void main(String args[]) {
    int[] val = new int[]{497978859, 167261111, 483575207, 591815159};
    MaxProf m = new MaxProf();
    System.out.println(m.maxProfit(val, 836556809));

    System.out.println(m.sumAll(76 - 22, 76));

  }

}
