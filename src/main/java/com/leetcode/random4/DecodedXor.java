package com.leetcode.random4;

import java.util.Arrays;

public class DecodedXor {

  public int[] decode(int[] encoded) {
    int total = 1;
    for (int i = 2; i <= encoded.length + 1; i++) {
      total = total ^ i;
    }
    int zero = total;
    for (int i = 1; i < encoded.length; i += 2) {
      zero = zero ^ encoded[i];
    }
    int[] ans = new int[encoded.length + 1];
    ans[0] = zero;
    for (int i = 1; i < ans.length; i++) {
      int first = ans[i - 1];
      int second = first ^ encoded[i - 1];
      ans[i] = second;
    }
    return ans;
  }


  public static void main(String args[]) {
    DecodedXor d = new DecodedXor();
    System.out.println(Arrays.toString(d.decode(new int[]{6, 5, 4, 6})));
  }

}
