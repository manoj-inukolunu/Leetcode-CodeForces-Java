package com.contest.codeforces.educational101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

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
      int len = f.nextInt();
      int d = f.nextInt();
      int[] arr = f.readArray(len);
      boolean found = true;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] > d) {
          found = false;
          break;
        }
      }
      if (found) {
        System.out.println("YES");
        continue;
      }
      for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr.length; j++) {
          if (i != j && arr[i] + arr[j] <= d) {
            System.out.println("YES");
            continue top;
          }
        }
      }
      System.out.println("NO");
    }
  }

}
