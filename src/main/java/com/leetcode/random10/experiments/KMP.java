package com.leetcode.random10.experiments;

import java.util.Arrays;

public class KMP {


  private int[] prefixFunc(String str) {
    int[] res = new int[str.length()];

    res[0] = 0;
    int k = 0;
    for (int i = 1; i < str.length(); i++) {
      while (k > 0 && str.charAt(i) != str.charAt(k)) {
        k = res[k - 1];
      }
      if (str.charAt(k) == str.charAt(i)) {
        k++;
      }
      res[i] = k;
    }

    return res;
  }


  public static void main(String args[]) {
    KMP kmp = new KMP();
    int[] pre = kmp.prefixFunc("abracadabra");
    System.out.println(Arrays.toString(pre));
  }


}
