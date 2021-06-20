package com.leetcode.random;

import java.util.HashSet;
import java.util.Objects;

public class LongestConsecOnes {

  class Hold {

    int curr;
    int left;
    int diag;
    int top;
    int anti;

    public Hold(int curr) {
      this.curr = curr;
    }

    public Hold(int curr, int left, int diag, int top, int anti) {
      this.curr = curr;
      this.left = left;
      this.diag = diag;
      this.top = top;
      this.anti = anti;
    }
  }

  boolean inside(int row, int col, int[][] M) {
    return row >= 0 && col >= 0 && row < M.length && col < M[row].length;
  }

  private int max(int a, int b, int c, int d) {
    return Math.max(Math.max(a, b), Math.max(c, d));
  }

  class Pair {

    int start;
    int end;

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return start == pair.start &&
          end == pair.end;
    }

    @Override
    public int hashCode() {
      return Objects.hash(start, end);
    }

    public Pair(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  int ans = Integer.MIN_VALUE;


  public int longestLine(int[][] M) {
    HashSet<Pair> horiz = new HashSet<>();
    HashSet<Pair> vert = new HashSet<>();
    HashSet<Pair> diag = new HashSet<>();
    HashSet<Pair> antidiag = new HashSet<>();

    for (int i = 0; i < M.length; i++) {
      for (int j = 0; j < M[i].length; j++) {
        if (M[i][j] == 1 && !horiz.contains(new Pair(i, j))) {
          int col = j;
          int curr = 0;
          while (col < M[i].length) {
            if (M[i][col] == 1) {
              curr++;
              horiz.add(new Pair(i, col));
            } else {
              break;
            }
            col++;
          }
          ans = Math.max(ans, curr);
        }
        if (M[i][j] == 1 && !vert.contains(new Pair(i, j))) {
          int row = i;
          int curr = 0;
          while (row < M.length) {
            if (M[row][j] == 1) {
              curr++;
              vert.add(new Pair(row, j));
            } else {
              break;
            }
            row++;
          }
          ans = Math.max(ans, curr);
        }
        if (M[i][j] == 1 && !diag.contains(new Pair(i, j))) {
          int row = i, col = j, curr = 0;
          while (inside(row, col, M)) {
            if (M[row][col] == 1) {
              curr++;
              diag.add(new Pair(row, col));
            } else {
              break;
            }
            row++;
            col++;
          }
          ans = Math.max(ans, curr);
        }
        if (M[i][j] == 1 && !antidiag.contains(new Pair(i, j))) {
          int row = i, col = j, curr = 0;
          while (inside(row, col, M)) {
            if (M[row][col] == 1) {
              curr++;
              antidiag.add(new Pair(row, col));
            } else {
              break;
            }
            row--;
            col++;
          }
          ans = Math.max(ans, curr);
        }
      }
    }

    return ans;
  }


  public static void main(String args[]) {
    int[][] arr = new int[][]{
        {0, 0}, {1, 1}
    };
    LongestConsecOnes l = new LongestConsecOnes();
    System.out.println(l.longestLine(arr));
  }
}
