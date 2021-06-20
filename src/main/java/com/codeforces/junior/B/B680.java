package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B680 {

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
    int n = scanner.nextInt();
    int a = scanner.nextInt();

    int[] arr = scanner.readArray(n);
    int start = a - 1, end = a - 1, total = 0;
    while (true) {
      if (start < 0 && end >= arr.length) {
        break;
      }
      int count;
      if (start == end) {
        total += arr[start];
      } else if (start >= 0 && end < arr.length) {
        count = arr[start] + arr[end];
        if (count == 2) {
          total += 2;
        }
      } else if (start >= 0) {
        if (arr[start] == 1) {
          total++;
        }
      } else {
        if (arr[end] == 1) {
          total++;
        }
      }
      start--;
      end++;
    }
    System.out.println(total);

  }
}
