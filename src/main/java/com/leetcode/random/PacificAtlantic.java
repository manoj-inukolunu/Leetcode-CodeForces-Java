package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PacificAtlantic {

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

  List<List<Integer>> ans = new ArrayList<>();

  Set<Pair> pacific = new HashSet<>();
  Set<Pair> atlantic = new HashSet<>();

  boolean inside(int row, int col, int[][] g) {
    return row >= 0 && col >= 0 && row < g.length && col < g[row].length;
  }

  public List<List<Integer>> pacificAtlantic(int[][] matrix) {

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        HashSet<Pair> visited = new HashSet<>();
        boolean[] res = dfs(i, j, matrix, pacific, atlantic, visited);
        if (res[0] && res[1]) {
          List<Integer> list = new ArrayList<>();
          list.add(i);
          list.add(j);
          ans.add(list);
        }
      }
    }
    return ans;
  }

  private boolean[] dfs(int row, int col, int[][] matrix, Set<Pair> pacific, Set<Pair> atlantic, HashSet<Pair> visited) {
    Pair curr = new Pair(row, col);
    if ((row == matrix.length - 1 && col == 0) || (row == 0 && col == matrix[row].length)) {
      return new boolean[]{true, true};
    }
    if (row == 0 || col == 0) {
      pacific.add(curr);
    }
    if (row == matrix.length - 1 || col == matrix[row].length - 1) {
      atlantic.add(curr);
    }

    if (!visited.contains(curr)) {
      visited.add(curr);
      int[][] dirs = new int[][]{
          {0, 1}, {0, -1}, {1, 0}, {-1, 0}
      };
      boolean p = false, a = false;
      for (int[] dir : dirs) {
        int nextR = dir[0] + row;
        int nextC = dir[1] + col;
        if (inside(nextR, nextC, matrix) && matrix[nextR][nextC] <= matrix[row][col]) {
          boolean[] ret = dfs(nextR, nextC, matrix, pacific, atlantic, visited);
          p = ret[0] || p;
          a = ret[1] || a;
        }
      }
      if (p) {
        pacific.add(curr);
      }
      if (a) {
        atlantic.add(curr);
      }
      return new boolean[]{p, a};
    }

    if (pacific.contains(curr) && atlantic.contains(curr)) {
      return new boolean[]{true, true};
    } else if (pacific.contains(curr) && !atlantic.contains(curr)) {
      return new boolean[]{true, false};
    } else if (!pacific.contains(curr) && atlantic.contains(curr)) {
      return new boolean[]{false, true};
    } else {
      return new boolean[]{false, false};
    }
  }


  public static void main(String args[]) {
    PacificAtlantic p = new PacificAtlantic();

    int[][] grid = new int[][]{
        {1, 2, 2, 3, 5},
        {3, 2, 3, 4, 4},
        {2, 4, 5, 3, 1},
        {6, 7, 1, 4, 5},
        {5, 1, 1, 2, 4}
    };

    int[][] grid1 = new int[][]{
        {1, 1}, {1, 1}, {1, 1}
    };
    System.out.println(p.pacificAtlantic(grid1));

  }
}
