package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BRepainting {

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
    int numT = f.nextInt();
    while (numT-- > 0) {
      int houses = f.nextInt(), num = f.nextInt();
      int[] arr = f.readArray(houses);
      int maxC = 1;
      for (int i = 0; i < arr.length; i++) {
        maxC = Math.max(arr[i], maxC);
      }
      int ans = Integer.MAX_VALUE;
      for (int c = 1; c <= maxC; c++) {
        int count = 0;
        for (int i = 0; i < arr.length; ) {
          if (arr[i] != c) {
            count++;
            i += num;
          } else {
            i++;
          }
        }
        ans = Math.min(ans, count);
      }
      System.out.println(ans);
    }
  }


}
