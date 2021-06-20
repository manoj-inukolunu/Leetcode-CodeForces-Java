package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CLunar {

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

  public static void main(String[] args) {
    FastScanner f = new FastScanner();
    int len = f.nextInt();
    Integer[] arr = f.readArray(len);
    Arrays.sort(arr);
    long sum = 0;
    for (int i = 0; i < arr.length / 2; i++) {
      sum += Math.pow((arr[i] + arr[arr.length - i - 1]), 2);
    }
    System.out.println(sum);
  }


}
