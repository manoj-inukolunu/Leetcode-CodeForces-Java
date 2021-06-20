package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BDecoding {

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

  //foerdcoecs

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int len = f.nextInt();
    String str = f.next();
    StringBuffer buffer = new StringBuffer();
    Queue<Character> queue = new LinkedList<>();
    for (int i = 0; i < str.length(); i++) {
      queue.add(str.charAt(i));
    }
    int dir;
    if (len % 2 == 0) {
      dir = 1;
    } else {
      dir = 0;
    }
    while (!queue.isEmpty()) {
      Character ch = queue.poll();
      if (dir == 0) {
        buffer.append(ch);
        dir = 1;
      } else {
        buffer.insert(0, ch);
        dir = 0;
      }
    }
    System.out.println(buffer.toString());
  }
}
