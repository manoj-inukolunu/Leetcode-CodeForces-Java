package com.leetcode.random6;

public class RobotBounded {

  public static void main(String args[]) {
    int num = (int) Math.pow(10, 9), count = 0;
    while (num > 0) {
      if (num % 2 == 0) {
        count++;
      } else {
        count += 2;
      }
      num /= 2;
    }
    System.out.println(count - 1);
    System.out.println(num);
  }
}
