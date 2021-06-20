package com.leetcode.random;

import java.math.BigInteger;

public class NumStr1 {

  int mod = (int) Math.pow(10, 9) + 7;

  public int numSub(String s) {
    BigInteger ans = BigInteger.ZERO;
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == '1') {
        int count = 0;
        while (i < s.length() && s.charAt(i) == '1') {
          count++;
          i++;
        }
//        ans = ans.add(num.add(BigInteger.ONE).multiply(num).divide(BigInteger.valueOf(2)));
      } else {
        i++;
      }
    }

    return ans.mod(BigInteger.valueOf(mod)).intValue();
  }

  public static void main(String args[]) {
    NumStr1 n = new NumStr1();
    System.out.println(n.numSub("000"));
  }

}
