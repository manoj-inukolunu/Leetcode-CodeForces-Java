package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class B608 {

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


  static int dist(BigInteger n1, BigInteger n2) {
    BigInteger x = n1.xor(n2);
    int setBits = 0;
    while (x.compareTo(BigInteger.ZERO) > 0) {
      setBits += x.and(BigInteger.ONE).intValue();
      x = x.shiftRight(1);
    }
    return setBits;
  }

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    String a = f.next();
    BigInteger aVal = new BigInteger(a, 2);
    String b = f.next();
    StringBuffer s = new StringBuffer(b.substring(0, a.length()));
    BigInteger curr = new BigInteger(b.substring(0, a.length()), 2);
    long sum = dist(aVal, curr);
    for (int i = 1; i + a.length() <= b.length(); i++) {
      s.deleteCharAt(0);
      s.append(b.charAt(i + a.length() - 1));
      curr = new BigInteger(s.toString(), 2);
      sum += dist(curr, aVal);
    }
    System.out.println(sum);
  }

}
