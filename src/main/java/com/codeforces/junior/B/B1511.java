package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1511 {

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


  static class Equation {

    //ax+by=c;
    int a, b, c;

    public Equation(int x1, int y1, int x2, int y2) {
      int a = y2 - y1;
      int b = x1 - x2;
      int c = a * x1 + b * y1;
    }

    public Equation(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
    }

    public boolean isPointOnLine(int x, int y) {
      return a * x + b * y == c;
    }
  }

  public static void main(String args[]) {
//    FastScanner scanner = new FastScanner();
    for (int i = 1; i <= 9; i++) {
      for (int j = 1; j <= 9; j++) {
        for (int k = 1; k <= Math.min(i, j); k++) {
          System.out.println(solve(i, j, k));
        }
      }
    }

  }

  private static boolean solve(int a, int b, int c) {
    StringBuffer buff = new StringBuffer("");
    for (int i = 0; i < c; i++) {
      buff.append("9");
    }
    return true;
  }
}
