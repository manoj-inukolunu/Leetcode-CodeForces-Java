package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B363 {

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
    FastScanner scanner = new FastScanner();
    int len = scanner.nextInt();
    int k = scanner.nextInt();
    int[] arr = scanner.readArray(len);
    int min = Integer.MAX_VALUE, sum = 0, idx = 0;
    for (int i = 0; i < k; i++) {
      sum += arr[i];
    }
    min = sum;
    for (int i = 1; i + k <= arr.length; i++) {
      sum -= arr[i - 1];
      sum += arr[i + k - 1];
      if (sum < min) {
        min = sum;
        idx = i;
      }
    }
    System.out.println(idx + 1);
  }
}
