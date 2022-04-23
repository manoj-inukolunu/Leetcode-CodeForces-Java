import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class CKingsPath {

  public String customSortString(String order, String s) {
    char[] arr = order.toCharArray();
    return new String(arr);
  }

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

    int row;
    int col;
    int steps;

    public Pair(int row, int col, int steps) {
      this.row = row;
      this.col = col;
      this.steps = steps;
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
      return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  private static boolean inside(int row, int col) {
    return row >= 1 && col >= 1 && row <= Math.pow(10, 9) && col <= Math.pow(10, 9);
  }

  public static void main(String args[]) {
    FastScanner f = new FastScanner();
    int sx = f.nextInt(), sy = f.nextInt(), ex = f.nextInt(), ey = f.nextInt();
    int numTest = f.nextInt();
    HashMap<Integer, List<Integer[]>> valid = new HashMap<>();
    while (numTest-- > 0) {
      int vR = f.nextInt();
      List<Integer[]> vList = valid.getOrDefault(vR, new ArrayList<>());
      vList.add(new Integer[] {f.nextInt(), f.nextInt()});
      valid.put(vR, vList);
    }
    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(sx, sy, 0));
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (curr.row == ex && curr.col == ey) {
        System.out.println(curr.steps);
        return;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        int[][] dirs =
            new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
        for (int[] dir : dirs) {
          int nX = curr.row + dir[0];
          int nY = curr.col + dir[1];
          if (inside(nX, nY) && possible(valid, nX, nY)) {
            queue.add(new Pair(nX, nY, curr.steps + 1));
          }
        }
      }
    }
    System.out.println(-1);
  }

  private static boolean possible(HashMap<Integer, List<Integer[]>> valid, int nX, int nY) {
    if (valid.containsKey(nX)) {
      for (Integer[] integers : valid.get(nX)) {
        if (nY >= integers[0] && nY <= integers[1]) {
          return true;
        }
      }
    }
    return false;
  }
}
