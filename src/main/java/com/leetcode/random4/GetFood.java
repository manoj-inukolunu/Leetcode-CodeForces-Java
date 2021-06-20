package com.leetcode.random4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class GetFood {

  class Pair {

    int row;
    int col;
    int dist;

    public Pair(int row, int col, int dist) {
      this.row = row;
      this.col = col;
      this.dist = dist;
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

  public int getFood(char[][] grid) {
    LinkedList<Pair> queue = new LinkedList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == '*') {
          queue.add(new Pair(i, j, 0));
          break;
        }
      }
    }
    HashSet<Pair> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      Pair curr = queue.poll();
      if (grid[curr.row][curr.col] == '#') {
        return curr.dist;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
        int[][] dist = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dist) {
          int nextR = curr.row + d[0];
          int nextC = curr.col + d[1];
          if (inside(nextR, nextC, grid) && grid[nextR][nextC] != 'X') {
            queue.add(new Pair(nextR, nextC, curr.dist + 1));
          }
        }
      }
    }
    return -1;
  }

  private boolean inside(int row, int col, char[][] grid) {
    return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }

}
