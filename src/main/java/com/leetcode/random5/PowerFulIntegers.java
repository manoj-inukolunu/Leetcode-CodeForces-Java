package com.leetcode.random5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerFulIntegers {

  public List<Integer> powerfulIntegers(int x, int y, int bound) {
    Set<Integer> set = new HashSet();
    for (int i = 0; i <= 15; i++) {
      for (int j = 0; j <= 15; j++) {
        long val = (long) Math.pow(x, i) + (long) Math.pow(y, j);
        if (val <= bound && val < Integer.MAX_VALUE && val > 0) {
          set.add((int) val);
        }
      }
    }
    return new ArrayList(set);
  }

  public static void main(String args[]) {
    PowerFulIntegers p = new PowerFulIntegers();
    System.out.println(p.powerfulIntegers(2, 91, 996));
  }

}
