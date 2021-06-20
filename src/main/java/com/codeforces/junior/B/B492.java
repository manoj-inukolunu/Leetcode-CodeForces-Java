package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B492 {

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

    Integer[] readArray(int n) {
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
    int len = scanner.nextInt();
    int l = scanner.nextInt();
    Integer[] arr = scanner.readArray(len);
    Arrays.sort(arr);
    int max = Integer.MIN_VALUE;
    for (int i = 0; i + 1 < arr.length; i++) {
      max = Math.max(arr[i + 1] - arr[i], max);
    }
    double ans = (double) max / 2;
    if (arr[0] != 0) {
      ans = Math.max(ans, arr[0]);
    }
    if (arr[arr.length - 1] != l) {
      ans = Math.max(ans, l - arr[arr.length - 1]);
    }
    System.out.println(ans);
  }
}
