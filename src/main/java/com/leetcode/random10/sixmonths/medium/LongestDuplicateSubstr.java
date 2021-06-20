package com.leetcode.random10.sixmonths.medium;

import java.util.HashSet;

public class LongestDuplicateSubstr {

  int mod = (int) (Math.pow(10, 9) + 9);
  int p = 31;

  long pPow[];
  long preHash[];


  private void generatePow(int len) {
    long[] pow = new long[len];
    pow[0] = 1;
    for (int i = 1; i < len; i++) {
      pow[i] = (pow[i - 1] * p) % mod;
    }
    pPow = pow;
  }

  private void genPreHash(int n, String str) {
    long[] prefixHash = new long[n + 1];
    for (int i = 0; i < n; i++) {
      prefixHash[i + 1] = (prefixHash[i] + (str.charAt(i) - 'a' + 1) * pPow[i]) % mod;
    }
    preHash = prefixHash;
  }

  public String longestDupSubstring(String S) {

    String ans = "";
    int start = 1, end = S.length();
    while (start <= end) {
      int mid = start + (end - start) / 2;
      int ret = exists(S, mid);
      if (ret != -1) {
        if (mid > ans.length()) {
          ans = S.substring(ret, ret + mid);
        }
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return ans;

  }

  public int exists(String str, int len) {
    generatePow(str.length());
    genPreHash(str.length(), str);
    int n = str.length();
    HashSet<Long> set = new HashSet<>();
    for (int i = 0; i <= n - len; i++) {
      long curr = (preHash[i + 1] + mod - preHash[i]) % mod;
      curr = (curr * pPow[n - i - 1]) % mod;
      if (set.contains(curr)) {
        return i;
      } else {
        set.add(curr);
      }
    }
    return -1;
  }

  public static void main(String args[]) {
    LongestDuplicateSubstr l = new LongestDuplicateSubstr();
    System.out.println(l.exists("bananaaafsfasdfasdf", 10));
  }

}
