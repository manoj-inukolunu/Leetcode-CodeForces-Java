package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BProsp {

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

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int n = f.nextInt();
    if (n == 1) {
      System.out.println("4");
      return;
    }
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < n / 2; i++) {
      buffer.append("8");
    }
    if (n % 2 != 0) {
      buffer.append("0");
    }
    if (buffer.length() >= 19) {
      System.out.println("-1");
      return;
    }
    System.out.println(buffer.toString());
  }

}
