package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LargeDig {

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
    int num = f.nextInt();
    List<Long[]> list = new ArrayList<>();
    long aoki = 0, tak = 0;
    while (num-- > 0) {
      long a = f.nextInt();
      long t = f.nextInt();
      list.add(new Long[]{a, t});
      aoki += a;
    }
    list.sort((o1, o2) -> -Long.compare(o1[0] + o1[1], o2[0] + o2[1]));
    int min = 0, idx = 0;
    while (tak <= aoki) {
      tak = tak + (list.get(idx)[0] + list.get(idx)[1]);
      aoki -= list.get(idx)[0];
      min++;
      idx++;
    }

    System.out.println(min);

  }

  private static double slopeF(int[] ints, int[] ints1) {
    return (double) (ints1[1] - ints[1]) / (double) (ints1[0] - ints[0]);
  }

  private static int sum(String valueOf) {
    int sum = 0;
    for (char ch : valueOf.toCharArray()) {
      sum += Character.getNumericValue(ch);
    }
    return sum;
  }

}
