package com.leetcode.random10.sixmonths.medium;

/**
 * @author manoji on 8/6/20.
 */
public class Divide {

  public int divide(int dividend, int divisor) {
    boolean negative = false;
    if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
      negative = true;
    }
    long div = Math.abs(Long.valueOf(dividend));
    long sor = Math.abs(Long.valueOf(divisor));
    if (sor == 1) {
      if (div >= Integer.MAX_VALUE) {
        if (negative) {
          return Integer.MIN_VALUE;
        } else {
          return Integer.MAX_VALUE;
        }
      } else {
        return (int) (negative ? (-div) : (div));
      }
    }

    int i = 0;
    int sum = (int) sor;
    while (sum <= div) {
      sum += sor;
      ++i;
    }
    return negative ? -i : i;
  }

  private int divide1(int dividend, int divisor) {

    // Calculate sign of divisor i.e.,
    // sign will be negative only iff
    // either one of them is negative
    // otherwise it will be positive
    int sign = ((dividend < 0) ^
        (divisor < 0)) ? -1 : 1;

    // Update both divisor and
    // dividend positive
    dividend = Math.abs(dividend);
    divisor = Math.abs(divisor);

    // Initialize the quotient
    int quotient = 0;

    while (dividend >= divisor) {
      dividend -= divisor;
      ++quotient;
    }

    return sign * quotient;
  }

  public static void main(String args[]) {
    Divide divide = new Divide();

    System.out.println(divide.divide1(2147483647, 2));
  }

}
