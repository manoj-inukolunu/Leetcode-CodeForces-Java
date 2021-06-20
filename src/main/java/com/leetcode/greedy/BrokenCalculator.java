package com.leetcode.greedy;


public class BrokenCalculator {

  private boolean isOdd(int num) {
    return (num % 2 == 0);
  }

  public int brokenCalc(int X, int Y) {
    if (X > Y) {
      return X - Y;
    }
    if (X == Y) {
      return 0;
    }
    if (isOdd(Y)) {
      return brokenCalc(X, Y + 1) + 1;
    } else {
      return brokenCalc(X, Y / 2) + 1;
    }
  }

  public static void main(String args[]) {
    BrokenCalculator b = new BrokenCalculator();
    System.out.println(b.brokenCalc(5, 8));
  }

}
