package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BKirmai {

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
    long[] pre = new long[arr.length];
    pre[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      pre[i] = pre[i - 1] + arr[i];
    }
    Arrays.sort(arr);
    long[] spre = new long[arr.length];
    spre[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      spre[i] = spre[i - 1] + arr[i];
    }
    int numT = f.nextInt();
    while (numT-- > 0) {
      int type = f.nextInt();
      if (type == 1) {
        System.out.println(getSum(pre, f.nextInt() - 1, f.nextInt() - 1));
      } else {
        System.out.println(getSum(spre, f.nextInt() - 1, f.nextInt() - 1));
      }
    }
  }

  private static long getSum(long[] spre, int a, int b) {
    if (a > 0) {
      return spre[b] - spre[a - 1];
    }
    return spre[b];
  }

}
