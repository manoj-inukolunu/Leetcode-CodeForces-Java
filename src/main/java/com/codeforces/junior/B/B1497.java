package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1497 {

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
    int numT = scanner.nextInt();
    while (numT-- > 0) {
      int len = scanner.nextInt();
      int m = scanner.nextInt();
      int[] arr = scanner.readArray(len);
      HashMap<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < arr.length; i++) {
        map.put(arr[i] % m, map.getOrDefault(arr[i] % m, 0) + 1);
      }
      System.out.println(map);
      System.out.println(map.size());
    }


  }
}
