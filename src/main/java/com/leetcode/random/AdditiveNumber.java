package com.leetcode.random;


import java.math.BigInteger;

public class AdditiveNumber {

  public boolean isAdditiveNumber(String num) {
    if (num.length() < 3) {
      return false;
    }
    for (int i = 1; i < num.length(); i++) {
      for (int j = i + 1; j < num.length(); j++) {
        String str1 = num.substring(0, i);
        if (str1.startsWith("0") && !str1.equals("0")) {
          continue;
        }
        String str2 = num.substring(i, j);
        if (str2.startsWith("0") && !str2.equals("0")) {
          continue;
        }
        BigInteger num1 = new BigInteger(str1);
        BigInteger num2 = new BigInteger(str2);
        if (dfs(num1, num2, j, num)) {
          return true;
        }
      }
    }
    return false;
  }


  private boolean dfs(BigInteger num1, BigInteger num2, int idx, String str) {
    if (idx >= str.length()) {
      return true;
    }

    String val = String.valueOf(num1.add(num2));
    if (str.indexOf(val, idx) != idx) {
      return false;
    }
    num1 = num2;
    num2 = new BigInteger(val);
    return dfs(num1, num2, idx + val.length(), str);
  }

  public static void main(String args[]) {
    AdditiveNumber a = new AdditiveNumber();
    System.out.println(a.isAdditiveNumber("121474836472147483648"));
  }
}
