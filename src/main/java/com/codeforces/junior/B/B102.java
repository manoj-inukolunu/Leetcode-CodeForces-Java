package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B102 {

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
    String str = scanner.next();
    char[] arr = str.toCharArray();
    int count = 0;
    while (arr.length != 1) {
      count++;
      long val = 0;
      for (int i = 0; i < arr.length; i++) {
        val += Character.getNumericValue(arr[i]);
      }
      arr = String.valueOf(val).toCharArray();
    }
    System.out.println(count);
  }
}
