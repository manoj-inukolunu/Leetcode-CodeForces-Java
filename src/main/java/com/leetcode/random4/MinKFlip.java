package com.leetcode.random4;

public class MinKFlip {

  public int minKBitFlips(int[] A, int K) {
    int count = 0;
    int i = 0;
    while (i < A.length) {
      if (A[i] == 0) {
        count++;
        int start = i;
        if (start + K > A.length) {
          return -1;
        }
        while (start < i + K && start < A.length) {
          A[start] = (A[start] == 0 ? 1 : 0);
          start++;
        }
        for (int j = i; j < A.length; j++) {
          if (A[j] != 1) {
            i = j;
            break;
          }
        }
      } else {
        i++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    MinKFlip m = new MinKFlip();
    System.out.println(m.minKBitFlips(new int[]{0, 1, 0, 0, 1, 0}, 4));
  }

}
