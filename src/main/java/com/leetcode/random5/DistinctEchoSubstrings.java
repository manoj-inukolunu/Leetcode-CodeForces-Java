package com.leetcode.random5;

import java.util.HashSet;

public class DistinctEchoSubstrings {


  public int distinctEchoSubstrings(String text) {
    HashSet<Long> set = new HashSet();

    long[][] arr = new long[text.length() + 1][text.length() + 1];

    for (int i = 0; i < text.length(); i++) {
      int p = 31;
      int mod = (int) (1e9 + 7);
      long hash = 0;
      long pPow = 1;
      for (int j = i; j < text.length(); j++) {
        hash = (hash + (text.charAt(j) - 'a' + 1) * pPow) % mod;
        pPow = (pPow * p) % mod;
        arr[i][j] = hash;
        //  System.out.println(text.substring(i, j + 1) + " " + hash);
      }
    }

    for (int i = 2; i <= text.length(); i += 2) {
      process(text, i, set, arr);
    }
    return set.size();
  }

  private void process(String str, int len, HashSet<Long> set, long[][] arr) {
    for (int i = 0; i + len <= str.length(); i++) {
      if (isEcho(i, len, arr)) {
        set.add(arr[i][i + len / 2]);
      }
    }
  }

  private boolean isEcho(int start, int len, long[][] hashes) {
    long first = hashes[start][(start + len / 2) - 1];
    long second = hashes[start + len / 2][start + len - 1];
    return first == second;
  }

  public static void main(String args[]) {
    DistinctEchoSubstrings d = new DistinctEchoSubstrings();
    System.out.println(d.distinctEchoSubstrings("leetcodeleetcode"));

  }//804229588 //545892912
}
