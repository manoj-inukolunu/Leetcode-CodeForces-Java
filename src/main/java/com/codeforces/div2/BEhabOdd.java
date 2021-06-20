package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BEhabOdd {

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
    FastScanner f = new FastScanner();
    int len = f.nextInt();
    Integer[] arr = f.readArray(len);
    boolean odd = false, even = false;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] % 2 != 0) {
        odd = true;
      } else {
        even = true;
      }
      if (even && odd) {
        break;
      }
    }
    if (even && odd) {
      Arrays.sort(arr);
      for (int i = 0; i < arr.length; i++) {
        System.out.print(arr[i]);
        if (i != arr.length - 1) {
          System.out.print(" ");
        }
      }
    } else {
      for (int i = 0; i < arr.length; i++) {
        System.out.print(arr[i]);
        if (i != arr.length - 1) {
          System.out.print(" ");
        }
      }
    }
  }
}
