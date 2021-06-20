package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BStudents {

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
    int n = f.nextInt();
    int m = f.nextInt();
    int count = 0;
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    while (m-- > 0) {
      int first = f.nextInt();
      int second = f.nextInt();
      Set<Integer> set = map.getOrDefault(first, new HashSet<>());
      set.add(second);
      map.put(first, set);
      set = map.getOrDefault(second, new HashSet<>());
      set.add(first);
      map.put(second, set);
    }

  }

}
