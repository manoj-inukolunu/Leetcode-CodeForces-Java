package com.leetcode.random10.sixmonths.medium;

import java.util.HashMap;

/**
 * @author manoji on 8/3/20.
 */
public class FractionToDecimal {


  private String fractionToDecimal(int numerator, int denominator) {
    boolean negative = (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);
    long numr = Math.abs((long) numerator);
    long denr = Math.abs((long) denominator);
    HashMap<Long, Integer> map = new HashMap<>();
    long toAdd = (numr / denr);
    long rem = numr % denr;
    if (rem == 0) {
      return (numr / denr) + "";
    }
    StringBuffer buffer = new StringBuffer();

    while (rem != 0 && !map.containsKey(rem)) {
      map.put(rem, buffer.length());
      rem *= 10;
      long add = rem / denr;
      buffer.append(add);
      rem = rem % denr;
    }

    if (rem != 0) {
      buffer.insert(map.get(rem), "(");
      buffer.append(")");
    }
    buffer.insert(0, '.');
    buffer.insert(0, toAdd);
    if (negative) {
      buffer.insert(0, "-");
    }
    return buffer.toString();
  }


  public static void main(String args[]) {
    FractionToDecimal f = new FractionToDecimal();
    System.out.println(f.fractionToDecimal(-1, -2147483648));
  }

}
