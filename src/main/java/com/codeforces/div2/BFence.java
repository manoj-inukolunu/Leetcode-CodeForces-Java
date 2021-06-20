package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BFence {

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
    int len = f.nextInt(), k = f.nextInt();
    int[] arr = f.readArray(len);
    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += arr[i];
    }

    int min = Integer.MAX_VALUE, idx = 0;
    for (int i = 1; i + k < arr.length; i++) {
      int curr = sum - arr[i - 1] + arr[i + k - 1];
      sum = curr;
      if (curr < min) {
        min = curr;
        idx = i + 1;
      }
    }

    System.out.println(idx);

  }

}
