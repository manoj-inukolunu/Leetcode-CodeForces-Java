package com.leetcode.random6;

import java.util.Arrays;

public class PrefixXor {

  int getXor(int[] pre, int start, int end) {
    if (start > 0) {
      return pre[end] ^ pre[start - 1];
    }
    return pre[end];
  }

  public void process(int[] arr) {
    int[] pre = new int[arr.length];
    pre[0] = arr[0];
    for (int i = 1; i < pre.length; i++) {
      pre[i] = pre[i - 1] ^ arr[i];
    }
    System.out.println(Arrays.toString(pre));
  }

  public static void main(String args[]) {
    PrefixXor p = new PrefixXor();
    p.process(new int[]{1, 3, 4, 8});
  }

}
