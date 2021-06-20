/*
package com.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class A260 {

  static class FastScanner {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] readArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  private boolean canForm(StringBuffer str, int n, BigInteger b, int max) {
    if (n > max) {
      return new BigInteger(str.toString()).mod(b).equals(BigInteger.ZERO);
    }
    for (int i = 0; i <= 9; i++) {
      str.append(i);
      BigInteger bg = new BigInteger(str.toString());
      if (BigInteger(str.toString()).mod(b).equals(BigInteger.ZERO)) {
        
      }
    }


  }

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int a = f.nextInt(), b = f.nextInt(), n = f.nextInt();
    while (n-- > 0) {
      for (int i = 0; i <= 9; i++) {

      }
    }
  }

}
*/
