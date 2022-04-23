package com.leetcode.hard;

import java.math.BigInteger;

public class LC878 {


  int g, l;

  public int nthMagicalNumber(int n, int a, int b) {
    g = gcd(a, b);
    l = lcm(a, b);
    BigInteger low = BigInteger.ONE, high = BigInteger.valueOf(Long.MAX_VALUE), ans =
        BigInteger.ZERO;
    while (low.compareTo(high) <= 0) {
      BigInteger mid = low.add((high.subtract(low)).divide(BigInteger.valueOf(2)));
      if (feasable(mid, n, a, b)) {
        ans = mid;
        high = mid.subtract(BigInteger.ONE);
      } else {
        low = mid.add(BigInteger.ONE);
      }
    }
    System.out.println(ans);
    return ans.mod(BigInteger.valueOf((long) (Math.pow(10, 9) + 7))).intValue();
  }

  private boolean feasable(BigInteger num, int n, int a, int b) {
    if (g == a) {
      return countNumsDiv(num, a).compareTo(BigInteger.valueOf(n)) >= 0;
    } else if (g == b) {
      return countNumsDiv(num, b).compareTo(BigInteger.valueOf(n)) >= 0;
    }
    return
        countNumsDiv(num, a).add(countNumsDiv(num, b)).subtract(countNumsDiv(num, l)).compareTo(BigInteger.valueOf(n))
            >= 0;
  }

  private int lcm(int a, int b) {
    return (a * b) / gcd(a, b);
  }

  private int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  private BigInteger countNumsDiv(BigInteger num, int a) {
    if (num.compareTo(BigInteger.valueOf(a)) < 0) {
      return BigInteger.ZERO;
    }
    return num.divide(BigInteger.valueOf(a));
  }

  public static void main(String[] args) {
    LC878 l = new LC878();
    System.out.println(l.nthMagicalNumber(1000000000, 40000, 40000));
  }
}
