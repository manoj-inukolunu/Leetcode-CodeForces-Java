package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B129 {

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
    int m = scanner.nextInt();
    HashMap<Integer, Set<Integer>> map = new HashMap<>();
    while (m-- > 0) {
      int first = scanner.nextInt();
      int second = scanner.nextInt();
      Set<Integer> set = map.getOrDefault(first, new HashSet<>());
      set.add(second);
      map.put(first, set);
      set = map.getOrDefault(second, new HashSet<>());
      set.add(first);
      map.put(second, set);
    }
    int count = 0;
    while (true) {
      List<Integer> list = new ArrayList<>();
      for (int key : map.keySet()) {
        if (map.get(key).size() == 1) {
          list.add(key);
        }
      }
      if (list.isEmpty()) {
        break;
      }
      count++;
      for (int num : list) {
        if (map.get(num).isEmpty()) {
          map.remove(num);
          continue;
        }
        int curr = map.get(num).iterator().next();
        map.get(curr).remove(num);
        map.remove(num);
      }
    }
    System.out.println(count);
  }
}
