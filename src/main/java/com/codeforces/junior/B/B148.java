package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B148 {

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
    int vp = scanner.nextInt();
    int vd = scanner.nextInt();
    int t = scanner.nextInt();
    int f = scanner.nextInt();
    int c = scanner.nextInt();

    if (vd < vp) {
      System.out.println(0);
      return;
    }
    int count = 0;
    double distP = t * vp;
    while (distP < c) {
      double td = distP / (vd - vp);
      distP = distP + td * vp;
      if (distP >= c) {
        break;
      }
      double backT = distP / vd + f;
      distP = distP + backT * vp;
      count++;
    }
    System.out.println(count);
  }
}
