package com.leetcode.backtracking;

import org.checkerframework.checker.units.qual.C;

/**
 * @author manoji on 7/13/20.
 */
public class CountNumbers {

  public int countNumbersWithUniqueDigits(int n) {
    if (n == 1) {
      return 10;
    }
    int count = 9;
    for (int i = 0; i < n - 1; i++) {
      count *= (9 - i);
    }
    count += countNumbersWithUniqueDigits(n - 1);
    return count;
  }


  public static void main(String args[]) {
    CountNumbers c = new CountNumbers();

    System.out.println(c.countNumbersWithUniqueDigits(5));
  }

}
