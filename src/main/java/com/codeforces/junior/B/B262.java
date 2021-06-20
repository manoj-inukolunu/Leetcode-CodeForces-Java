package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B262 {

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

    Integer[] readArrayInt(int n) {
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
    int n = scanner.nextInt();
    int k = scanner.nextInt();
    Integer[] arr = scanner.readArrayInt(n);
    for (int i = 0; i < arr.length && k > 0; i++) {
      if (arr[i] < 0) {
        arr[i] *= -1;
        k--;
      } else {
        break;
      }
    }
    Arrays.sort(arr);
    if (k % 2 == 0) {
      System.out.println(Arrays.stream(arr).mapToInt(a -> a).sum());
    } else {
      arr[0] *= -1;
      System.out.println(Arrays.stream(arr).mapToInt(a -> a).sum());
    }
  }
}
