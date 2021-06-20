package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B352 {

  static class FastScanner {

    public int uniq;
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
      int[] counter = new int[100004];
      for (int i = 0; i < n; i++) {
        a[i] = nextInt();
        if (counter[a[i]] == 0) {
          uniq++;
        }
        counter[a[i]]++;
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

  private static PrintStream out = System.out;


  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    int[] arr = scanner.readArray(n);
    int count = scanner.uniq;
    boolean[] invalid = new boolean[100004];
    int[] counter = new int[100004];
    int[] diff = new int[100004];
    int[] idx = new int[100004];
    Arrays.fill(idx, -1);
    for (int i = 0; i < arr.length; i++) {
      int curr = arr[i];
      if (invalid[curr]) {
        continue;
      }
      counter[curr]++;
      if (idx[curr] != -1) {
        if (diff[curr] == 0) {
          diff[curr] = i - idx[curr];
          idx[curr] = i;
        } else if (i - idx[curr] != diff[curr]) {
          invalid[curr] = true;
          count--;
        }
        idx[curr] = i;
      } else {
        idx[curr] = i;
      }
    }
    out.println(count);
    StringBuilder sb = new StringBuilder();
    for (int x = 1; x <= 100000; x++) {
      if (counter[x] > 0 && !invalid[x]) {
        sb.append("" + x + " " + diff[x] + String.format("%n"));
      }
    }
    out.println(sb);
  }
}
