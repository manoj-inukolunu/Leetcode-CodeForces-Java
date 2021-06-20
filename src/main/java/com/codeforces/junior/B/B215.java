package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B215 {

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

    Integer[] readArrayInt(int n) {
      Integer[] a = new Integer[n];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    Integer[] r1 = scanner.readArrayInt(n);
    int m = scanner.nextInt();
    Integer[] p1 = scanner.readArrayInt(m);
    int k = scanner.nextInt();
    Integer[] p2 = scanner.readArrayInt(k);
    int A = scanner.nextInt();
    int B = scanner.nextInt();
    Arrays.sort(r1);
    double r2 = Double.MIN_VALUE;
    for (int i = 0; i < p1.length; i++) {
      for (int j = 0; j < p2.length; j++) {
        double val = r1[r1.length - 1] * Math.sqrt((double) (B * p1[i]) / (B * p1[i] + A * p2[j]));
        r2 = Math.max(r2, val);
      }
    }
    System.out.println(r2);
  }
}
