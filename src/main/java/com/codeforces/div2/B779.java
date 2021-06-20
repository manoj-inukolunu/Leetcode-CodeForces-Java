package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B779 {

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
    String num = String.valueOf(f.nextInt());
    int k = f.nextInt();
    int count = 0, del = 0;
    for (int i = num.length() - 1; i >= 0; i--) {
      if (num.charAt(i) == '0') {
        count++;
      } else {
        del++;
      }
      if (count == k && i != 0) {
        System.out.println(del);
        return;
      }
    }
    if (k >= num.length()) {
      System.out.println(num.length() - 1);
      return;
    }

    System.out.println(num.length() - 1);
  }

}
