package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B260 {

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

    Integer[] readIntegers(int n) {
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
    int k = f.nextInt();
    Integer[] arr = f.readIntegers(len);
    int sum = 0, count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < 0) {
        count++;
      }
    }
    if (count >= k) {
      for (int i = 0; i < arr.length; i++) {
        if (k > 0) {
          k--;
          sum += Math.abs(arr[i]);
        } else {
          sum += arr[i];
        }
      }
      System.out.println(sum);
    } else {
      for (int i = 0; i < arr.length; i++) {
        arr[i] = Math.abs(arr[i]);
      }
      Arrays.sort(arr);
      k -= count;
      if (k % 2 == 0) {
        for (int i = 0; i < arr.length; i++) {
          sum += arr[i];
        }
      } else {
        sum += (-arr[0]);
        for (int i = 1; i < arr.length; i++) {
          sum += arr[i];
        }
      }
      System.out.println(sum);
    }
  }

}
