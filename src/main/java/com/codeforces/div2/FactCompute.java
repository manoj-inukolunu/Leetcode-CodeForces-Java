package com.codeforces.div2;

import java.math.BigInteger;

public class FactCompute {

  public static void main(String[] args) {
    BigInteger b = BigInteger.ONE;
    for (int i = 1; i <= 100; i++) {
      b = b.multiply(BigInteger.valueOf(i));
    }
    System.out.println(b.toString());
  }

}
