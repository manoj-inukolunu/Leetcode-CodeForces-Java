package com.leetcode.math;

public class SquareOfNumbers {

  public boolean judgeSquareSum(int c) {
    int bMax = (int) Math.sqrt(c);
    for (int i = 0; i <= bMax; i++) {
      double val = Math.sqrt(c - i * i);
      int lower = (int) Math.floor(val);
      int higher = (int) Math.ceil(val);
      if (higher - lower == 0) {
        return true;
      }
    }

    return false;
  }

  public static void main(String args[]) {
    SquareOfNumbers s = new SquareOfNumbers();
    System.out.println(s.judgeSquareSum(1));
  }

}
