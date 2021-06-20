package com.leetcode.random7;

public class NumPowThree {

  public boolean checkPowersOfThree(int n) {
    return false;
  }


  public static void main(String args[]) {
    int i = 0;
    System.out.println(Math.pow(10, 7));
    while (true) {
      double val = Math.pow(3, i++);
      if (val > Math.pow(10, 7)) {
        break;
      }
      System.out.println(val);
    }
  }
}
