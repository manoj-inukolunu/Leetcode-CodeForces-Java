package com.leetcode.random;

public class KthGrammar {

  public int kthGrammar(int N, int K) {
    if (N == 1 && K == 1) {
      return 0;
    }
    if (N == 2 && K == 1) {
      return 0;
    }
    if (N == 2 && K == 2) {
      return 1;
    }

    int prev = kthGrammar(N - 1, (K % 2 == 0 ? K / 2 : (K + 1) / 2));
    if (prev == 1 && K % 2 == 0) {
      return 0;
    } else if (prev == 1) {
      return 1;
    } else if (prev == 0 && K % 2 == 0) {
      return 1;
    } else {
      return 0;
    }
  }


  public static void main(String args[]) {
    KthGrammar k = new KthGrammar();

    System.out.println(k.kthGrammar(30, 15));
  }

}
