package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B186 {

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

  static class Pair {

    double height;
    int num;

    public Pair(double height, int num) {
      this.height = height;
      this.num = num;
    }
  }

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    int n = scanner.nextInt();
    int t1 = scanner.nextInt();
    int t2 = scanner.nextInt();
    int k = scanner.nextInt();
    List<Pair> list = new ArrayList<>();
    int i = 1;
    while (n-- > 0) {
      int a = scanner.nextInt();
      int b = scanner.nextInt();
      list.add(new Pair(getHeight(t1, t2, a, b, k), i++));
    }
    Collections.sort(list, (o1, o2) -> {
      if (Double.compare(o1.height, o2.height) == 0) {
        return Integer.compare(o1.num, o2.num);
      }
      return -Double.compare(o1.height, o2.height);
    });
    NumberFormat formatter = new DecimalFormat("#0.00");
    for (i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).num + " " + formatter.format(list.get(i).height));
    }
  }

  static double getHeight(int t1, int t2, int a, int b, double k) {
    return Math.max(t1 * a - (t1 * a * (k / 100)) + t2 * b, t1 * b - (t1 * b * (k / 100)) + t2 * a);
  }
}
