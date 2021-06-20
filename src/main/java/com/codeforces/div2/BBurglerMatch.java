package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BBurglerMatch {

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

  static class Pair {

    int matchbox;
    int matches;

    public Pair(int matchbox, int matches) {
      this.matchbox = matchbox;
      this.matches = matches;
    }
  }

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int n = f.nextInt(), m = f.nextInt();
    List<Pair> list = new ArrayList<>();
    while (m-- > 0) {
      list.add(new Pair(f.nextInt(), f.nextInt()));
    }
    Collections.sort(list, (o1, o2) -> -Integer.compare(o1.matches, o2.matches));
    long count = 0;
    int i = 0;
    while (true) {
      if (list.isEmpty()) {
        break;
      }
      Pair p = list.get(i);
      if (n - p.matchbox > 0) {
        count += ((long) p.matchbox * p.matches);
        list.remove(i);
        n -= p.matchbox;
      } else {
        count += ((long) n * p.matches);
        break;
      }
    }
    System.out.println(count);
  }

}
