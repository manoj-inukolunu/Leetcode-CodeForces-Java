package com.leetcode.math;

public class PrimePalindrome {


  public int primePalindrome(int N) {
    for (int i = 0; i < 100000; i++) {
      String curr = String.valueOf(i);
      String rev = new StringBuffer(curr).reverse().toString();
      String next = curr + rev.substring(1);
      if (Long.parseLong(next) >= N && isPrime(Integer.parseInt(next))) {
        return Integer.parseInt(next);
      }
      next = curr + rev;
      if (Long.parseLong(next) >= N && isPrime(Integer.parseInt((next)))) {
        return Integer.parseInt(next);
      }
    }
    return -1;
  }

  boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }
    if (n == 2 || n == 3) {
      return true;
    }
    if (n % 2 == 0 || n % 3 == 0) {
      return false;
    }
    long sqrtN = (long) Math.sqrt(n) + 1;
    for (long i = 6L; i <= sqrtN; i += 6) {
      if (n % (i - 1) == 0 || n % (i + 1) == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    PrimePalindrome p = new PrimePalindrome();
    System.out.println(p.primePalindrome(9989900));
  }

}
