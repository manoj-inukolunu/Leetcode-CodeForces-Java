package com.leetcode.hard;

import java.util.Arrays;

public class LongestPrefix {


  int p = 101;
  int mod = 1000000007;

  private int modPow(int base, int exp) {
    if (exp == 0) {
      return 1;
    }
    if (exp == 1) {
      return base;
    }

    int tmp = modPow(base, exp / 2);
    if (exp % 2 == 0) {
      return (tmp % mod * tmp % mod) % mod;
    } else {
      return (((tmp % mod * tmp % mod) % mod) * base % mod) % mod;
    }
  }

  private int mmi(int n) {
    return modPow(n, mod - 2);
  }

  private void computePrefixHash(String str, int len, int[] prefix, int[] power) {
    prefix[0] = 0;
    prefix[1] = str.charAt(0);
    for (int i = 2; i <= len; i++) {
      prefix[i] = (prefix[i - 1] % mod + (str.charAt(i - 1) % mod * power[i - 1] % mod) % mod) % mod;
    }
  }

  private void computePowers(int power[], int n) {
    power[0] = 1;
    for (int i = 1; i <= n; i++) {
      power[i] = (power[i - 1] % mod * p % mod) % mod;
    }
  }

  public void hash(String str) {
    int n = str.length();
    int[] power = new int[n + 1];
    computePowers(power, n);
    int[] prefix = new int[n + 1];
    computePrefixHash(str, n, prefix, power);
    System.out.println(Arrays.toString(prefix));
    for (int i = str.length(); i >= 0; i--) {
      long hashLR = ((prefix[str.length()] - prefix[i - 1] + mod) % mod * mmi(power[i - 1]) % mod) % mod;
      System.out.println(hashLR);
    }
  }

  void computeLPSArray(String pat, int M, int lps[]) {
    int len = 0;
    int i = 1;
    lps[0] = 0;
    while (i < M) {
      if (pat.charAt(i) == pat.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else {
        if (len != 0) {
          len = lps[len - 1];
        } else {
          lps[i] = len;
          i++;
        }
      }
    }
  }

  private String longestPrefix(String str) {
    int len = str.length();
    int lps[] = new int[len];
    computeLPSArray(str, len, lps);
    System.out.println(Arrays.toString(lps));
    return str.substring(0, lps[str.length() - 1]);
  }

  public static void main(String args[]) {
    String s = "ababbabbababbababbabb";
    LongestPrefix l = new LongestPrefix();
    //System.out.println(l.longestPrefix(s));
    System.out.println(l.longestPrefix(s));
  }

}
