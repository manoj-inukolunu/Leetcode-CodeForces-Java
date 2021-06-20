package com.leetcode.random7;

import java.math.BigInteger;

public class SmallestDivK {

  public int smallestRepunitDivByK(int K) {
    BigInteger curr = BigInteger.ONE;
    int maxLen = (Long.MAX_VALUE + "").length();
    while (curr.toString().length() < maxLen) {
      if (curr.mod(BigInteger.valueOf(K)).intValue() == 0) {
        return String.valueOf(curr).length();
      }
      curr = new BigInteger(curr.toString() + "1");
    }

    return -1;
  }

  public static void main(String args[]) {
    SmallestDivK s = new SmallestDivK();
    System.out.println(s.smallestRepunitDivByK(17));
  }

}
