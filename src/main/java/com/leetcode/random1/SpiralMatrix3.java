package com.leetcode.random1;

import java.util.LinkedHashSet;
import java.util.Objects;

public class SpiralMatrix3 {

  boolean inside(int R, int C, int row, int col) {
    return row >= 0 && row < R && col >= 0 && col < C;
  }

  class Pair {

    int row;
    int col;

    public Pair(int row, int col) {
      this.row = row;
      this.col = col;
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

    @Override
    public String toString() {
      return "Pair{" +
          "row=" + row +
          ", col=" + col +
          '}';
    }
  }

  public int[][] spiralMatrixIII(int R, int C, int row, int col) {

    LinkedHashSet<Pair> set = new LinkedHashSet<>();
    set.add(new Pair(row, col));
    if (inside(R, C, row, col + 1)) {
      set.add(new Pair(row, col + 1));
    }
    col++;
    if (inside(R, C, row - 1, col)) {
      set.add(new Pair(row, col));
    }
    row++;

    int left = 2, up = 2, right = 3, down = 3, i = 1;

    while (set.size() < R * C) {
      while (left > 0) {
        left--;
        if (inside(R, C, row, col)) {
          set.add(new Pair(row, col));
        }
        col--;
      }
      left = 2 + 2 * i;
      while (up > 0) {
        up--;
        if (inside(R, C, row, col)) {
          set.add(new Pair(row, col));
        }
        row--;
      }
      up = 2 + 2 * i;
      while (right > 0) {
        right--;
        if (inside(R, C, row, col)) {
          set.add(new Pair(row, col));
        }
        col++;
      }
      right = 3 + 2 * i;
      while (down > 0) {
        down--;
        if (inside(R, C, row, col)) {
          set.add(new Pair(row, col));
        }
        row++;
      }
      down = 3 + 2 * i;
      i++;
    }

    int[][] ans = new int[set.size()][2];
    int j = 0;
    for (Pair pair : set) {
      ans[j++] = new int[]{pair.row, pair.col};
    }

    return ans;
  }

  public static void main(String[] args) {
    SpiralMatrix3 s = new SpiralMatrix3();
    s.spiralMatrixIII(5, 6, 1, 4);
  }

}
