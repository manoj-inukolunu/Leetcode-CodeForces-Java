package com.codeforces.junior.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B47 {

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

  static Set<String> valid = new HashSet<>();
  static String ans = "";

  public static void main(String args[]) {
    FastScanner scanner = new FastScanner();
    valid.add("ABC");
    valid.add("ACB");
    valid.add("BAC");
    valid.add("BCA");
    valid.add("CAB");
    valid.add("CBA");
    Set<String> queries = new HashSet<>();
    queries.add(scanner.next());
    queries.add(scanner.next());
    queries.add(scanner.next());
    for (String str : valid) {
      if (process(queries, str)) {
        continue;
      }
      if (ans.isEmpty()) {
        ans = str;
      } else {
        System.out.println("Impossible");
        return;
      }
    }

    System.out.println(ans.isEmpty() ? "Impossible" : ans);

  }

  private static boolean process(Set<String> queries, String str) {
    for (String curr : queries) {
      if (curr.contains("<")) {
        char first = curr.charAt(0);
        char second = curr.charAt(2);
        if (str.indexOf(first) >= str.indexOf(second)) {
          return true;
        }
      } else {
        char first = curr.charAt(2);
        char second = curr.charAt(0);
        if (str.indexOf(first) >= str.indexOf(second)) {
          return true;
        }
      }
    }
    return false;
  }
}
