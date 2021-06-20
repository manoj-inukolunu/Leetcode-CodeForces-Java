package com.contest.codeforces.educational101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {

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
    int numT = f.nextInt();
    top:
    while (numT-- > 0) {
      String a = f.next();
      String b = f.next();
      StringBuilder s = new StringBuilder(a);
      while (s.length() % b.length() > 0) {
        s.append(a);
      }
      String t = b;
      while (t.length() < s.length()) {
        t += b;
      }
      if (s.toString().equals(t)) {
        System.out.println(t);
      } else {
        System.out.println(-1);
      }

    }
  }

}
