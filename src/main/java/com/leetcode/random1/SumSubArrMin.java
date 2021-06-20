package com.leetcode.random1;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class SumSubArrMin {

  public int sumSubarrayMins(int[] A) {

    int n = A.length;
    int[] prevL = new int[n];
    int[] nextL = new int[n];
    Arrays.fill(nextL, n);
    Stack<Integer> decS = new Stack();
    for (int i = 0; i < n; i++) {
      while (!decS.isEmpty() && A[decS.peek()] > A[i]) {
        nextL[decS.pop()] = i;
      }
      prevL[i] = decS.isEmpty() ? -1 : decS.peek();
      decS.push(i);
    }
    BigInteger sum = BigInteger.ZERO;
    int mod = (int) (Math.pow(10, 9) + 7);
    for (int i = 0; i < A.length; i++) {
      BigInteger l = BigInteger.valueOf(nextL[i]).subtract(BigInteger.valueOf(i));
      BigInteger r = BigInteger.valueOf(i).subtract(BigInteger.valueOf(prevL[i]));
      l = l.multiply(r).multiply(BigInteger.valueOf(A[i]));
      sum = sum.add(l);
    }
    return sum.mod(BigInteger.valueOf(mod)).intValue();
  }

  public static void main(String args[]) {
    SumSubArrMin s = new SumSubArrMin();
    int[] arr = new int[]{1, 4, 1};
    System.out.println(s.sumSubarrayMins(arr));
//    s.numSubarrayBoundedMax(arr);
  }

}
