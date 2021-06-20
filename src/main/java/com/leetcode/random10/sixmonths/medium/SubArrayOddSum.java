package com.leetcode.random10.sixmonths.medium;

import java.math.BigInteger;
import java.util.HashMap;

public class SubArrayOddSum {


  static int mod = (int) Math.pow(10, 9) + 7;

  public int numOfSubarrays(int[] arr) {
    long len = arr.length;
    long len1 = len + 1;
    BigInteger bigInteger = BigInteger.valueOf(len);
    BigInteger b2 = BigInteger.valueOf(len1);
    bigInteger = bigInteger.multiply(b2);
    bigInteger = bigInteger.divide(BigInteger.valueOf(2));
    BigInteger div2 = subarraysDivByK(arr, 2);
    System.out.println(div2);
    bigInteger = bigInteger.subtract(div2);
    return bigInteger.mod(BigInteger.valueOf(mod)).intValue();
  }

  public BigInteger subarraysDivByK(int[] A, int K) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int sum = 0;
    for (int i = 0; i < A.length; i++) {
      sum = (sum + A[i]) % K;
      sum = (sum + K) % K;
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }
    BigInteger ans = BigInteger.valueOf(0);
    for (int key : map.keySet()) {
      int m = map.get(key);
      ans = ans.add(BigInteger.valueOf((m) * (m - 1) / 2));
      // ans += (m) * (m - 1) / 2;
    }

    return ans;
  }

  public static void main(String args[]) {
    long one = 57585;
    long two = 57584;

    long val = ((one % mod) * (two % mod)) % mod;
    System.out.println(val);
  }
}
