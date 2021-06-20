package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BMahmoud {

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
    int size = f.nextInt();
    int[] arr = f.readArray(size);
    Arrays.sort(arr);
    for (int i = 0; i + 2 < arr.length; i++) {
      if (form(arr[i], arr[i + 1], arr[i + 2])) {
        System.out.println("YES");
        return;
      }
    }
    System.out.println("NO");

  }

  private static boolean form(int a, int b, int c) {
    return a + b > c && b + c > a && a + c > b;
  }

}
