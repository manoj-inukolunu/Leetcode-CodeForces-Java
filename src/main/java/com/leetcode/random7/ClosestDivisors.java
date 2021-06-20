package com.leetcode.random7;

import java.util.Arrays;

public class ClosestDivisors {

  public int[] closestDivisors(int num) {
    System.out.println(num);
    int[] first = divisors(num + 1);
    int[] second = divisors(num + 2);
    if (Math.abs(first[1] - first[0]) < Math.abs(second[1] - second[0])) {
      return first;
    }
    return second;
  }

  private int[] divisors(int num) {
    int min = Integer.MAX_VALUE, num1 = 0, num2 = 0;
    for (int i = 1; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        int second = num / i;
        int diff = Math.abs(i - second);
        if (diff < min) {
          min = diff;
          num1 = i;
          num2 = second;
        }
      }
    }
    return new int[]{num1, num2};
  }

  public static void main(String[] args) {
    ClosestDivisors c = new ClosestDivisors();
    System.out.println(Arrays.toString(c.closestDivisors((int) Math.pow(10, 9))));
  }
}
