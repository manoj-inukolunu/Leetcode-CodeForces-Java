package com.codeforces.div2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class BKeyboard {

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

    char[] readArray(int n) {
      char[] a = new char[n];
      for (int i = 0; i < n; i++) {
        a[i] = next().charAt(0);
      }
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }

  static class Pair {

    int x;
    int y;
    char ch;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return x == pair.x && y == pair.y;
    }

    @Override
    public String toString() {
      return "Pair{" +
          "x=" + x +
          ", y=" + y +
          ", ch=" + ch +
          '}';
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  public static void main(String args[]) {
    /*FastScanner f = new FastScanner();
    int rows = f.nextInt();
    int cols = f.nextInt();
    int dist = f.nextInt();
    Pair[][] arr = new Pair[rows][cols];
    HashMap<Character, List<Pair>> map = new HashMap();
    Set<Pair> set = new HashSet<>();
    for (int i = 0; i < rows; i++) {
      String str = f.next();
      for (int j = 0; j < str.length(); j++) {
        arr[i][j] = new Pair(j, i);
        arr[i][j].ch = str.charAt(j);
        List<Pair> list = map.getOrDefault(arr[i][j].ch, new ArrayList<>());
        list.add(arr[i][j]);
        map.put(arr[i][j].ch, list);
        if (arr[i][j].ch == 'S') {
          set.add(arr[i][j]);
        }
      }
    }

    int len = f.nextInt();
    String str = f.next();
    int count = 0;

    HashMap<Character, Boolean> can = new HashMap<>();

    for (int i = 0; i < str.length(); i++) {
      char curr = str.charAt(i);
      if (!map.containsKey(Character.toLowerCase(curr))) {
        System.out.println(-1);
        return;
      }
      if (Character.isUpperCase(curr)) {
        if (can.containsKey(curr)) {
          if (!can.get(curr)) {
            count++;
          }
          continue;
        }
        if (set.isEmpty()) {
          System.out.println(-1);
          return;
        }
        boolean found = false;
        List<Pair> pairs = map.get(Character.toLowerCase(curr));
        for (Pair sPair : set) {
          for (Pair pair : pairs) {
            int x = pair.x - sPair.x;
            int y = pair.y - sPair.y;
            if (x * x + y * y <= dist * dist) {
              found = true;
              break;
            }
            if (found) {
              break;
            }
          }
        }
        can.put(curr, found);
        if (!found) {
          count++;
        }
      }
    }
    System.out.println(count);
  */

    System.out.println(solve("acveifqnss", new String[]{"a", "b", "c", "z"}));
  }


  public static String solve(String text, String[] patterns) {
    for (String str : patterns) {
      text = text.replaceAll(str, "<b>" + str + "</b>");
    }
    System.out.println(text);
    text = text.replaceAll("</b><b>", "");
    return text;
  }

}
