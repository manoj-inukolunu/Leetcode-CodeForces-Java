package com.leetcode.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLongestFib {

  class Pair {

    int num1;
    int num2;
    int len;
    int sum;

    public Pair(int num1, int num2) {
      this.num1 = num1;
      this.num2 = num2;
    }

    @Override
    public String toString() {
      return "Pair{" +
          "num1=" + num1 +
          ", num2=" + num2 +
          ", len=" + len +
          ", sum=" + sum +
          '}';
    }
  }

  public int lenLongestFibSubseq(int[] A) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; i++) {
      for (int j = i + 1; j < A.length; j++) {
        List<Integer> list = new ArrayList<>();
        list.add(A[i]);
        list.add(A[j]);
        int start = j + 1;
        int sum = A[i] + A[j];
        int len = 2;
        while (true) {
          int idx = Arrays.binarySearch(A, start, A.length, sum);
          if (idx < 0) {
            break;
          }
          len++;
          list.remove(0);
          list.add(A[idx]);
          sum = list.get(0) + list.get(1);
          start = idx + 1;
        }
        max = Math.max(len, max);
      }
    }
    return max;
  }

  public static void main(String args[]) {
    LengthOfLongestFib l = new LengthOfLongestFib();

    System.out.println(l.lenLongestFibSubseq(new int[]{2, 4, 5, 6, 7, 8, 11, 13, 14, 15, 21, 22, 34}));
  }

}
