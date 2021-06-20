package com.leetcode.random1;

public class Pow {

  public double myPow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    if (n % 2 == 0) {
      return myPow(x, n / 2) * myPow(x, n / 2);
    } else {
      int val = (n - 1) / 2;
      return (myPow(x, val / 2) * myPow(x, val / 2)) * x;
    }
  }

  public static void main(String args[]) {
    Pow p = new Pow();
  }

}
