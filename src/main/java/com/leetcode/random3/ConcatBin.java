package com.leetcode.random3;

public class ConcatBin {

  public int concatenatedBinary(int n) {
    int mod = (int) (Math.pow(10, 9) + 7);
    int val = 1;
    for (int i = 2; i <= n; i++) {
      val = (val << (Integer.toBinaryString(i).length())) % mod;
      val = (val % mod + i % mod) % mod;
    }
    return val % mod;
  }

  public static void main(String args[]) {
    ConcatBin c = new ConcatBin();
    System.out.println(c.concatenatedBinary(3));


  }


}
