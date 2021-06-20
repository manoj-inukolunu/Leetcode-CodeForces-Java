package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class NumberOfIslands2 {

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
      return row == pair.row &&
          col == pair.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }

  boolean inside(int row, int col, int mR, int nC) {
    return row >= 0 && col >= 0 && row < mR && col < nC;
  }

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    int[][] arr = new int[m][n];
    HashSet<HashSet<Pair>> components = new HashSet<>();
    List<Integer> ans = new ArrayList<>();
    int count = 0;
    for (int i = 0; i < positions.length; i++) {
      int[] pos = positions[i];
      if (arr[pos[0]][pos[1]] == 0) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int curr=0;
        for (int[] dir : dirs) {
          int nextR = pos[0] + dir[0];
          int nextC = pos[1] + dir[1];
          if (inside(nextR, nextC, m, n) && arr[nextR][nextC]==1) {
            curr++;
          }
        }

      }

    }
    return ans;
  }

}
